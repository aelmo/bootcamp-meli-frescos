package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseRequestProductsDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseOrderProductsResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.BadRequestApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.*;
import com.mercadolibre.bootcampmelifrescos.repository.*;
import com.mercadolibre.bootcampmelifrescos.service.PurchaseOrderService;
import com.mercadolibre.bootcampmelifrescos.service.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseStatusesRepository purchaseStatusesRepository;
    private final ProductRepository productRepository;
    private final BatchRepository batchRepository;
    private final BuyerRepository buyerRepository;
    private final PurchaseProductsRepository purchaseProductsRepository;
    private final Validator validator;


    @Override
    public PurchaseAmountDTO updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Long orderId) throws ApiException {
        if (purchaseOrderRepository.findById(orderId).isEmpty())
            throw new NotFoundApiException("Purchase order not found");
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(orderId).get();
        List<PurchaseOrderProducts> purchaseOrderProducts = purchaseProductsRepository.findAllByPurchaseOrder(purchaseOrder);

        for (PurchaseOrderProducts purchaseOrderProducts1: purchaseOrderProducts) {
            if (batchRepository.findById(purchaseOrderProducts1.getBatchId()).isEmpty()) {
                throw new NotFoundApiException("Batch not found");
            }
            Batch batch = batchRepository.findById(purchaseOrderProducts1.getBatchId()).get();
            batch.setCurrentQuantity(batch.getLastQuantity());
            batchRepository.save(batch);
        }

        purchaseProductsRepository.deleteAll(purchaseOrderProducts);

        createPurchaseOrderProductsSet(purchaseOrder, purchaseOrderDTO.getProducts());

        purchaseOrder.setDate(purchaseOrderDTO.getDate());
        purchaseOrder.setStatus(purchaseStatusesRepository.findById(purchaseOrderDTO.getOrderStatus().getStatusCode()).get());
        purchaseOrder.setBuyer(buyerRepository.findById(purchaseOrderDTO.getBuyerId()).get());

        return getAmountOfAnPurchaseOrder(purchaseOrderRepository.save(purchaseOrder));
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws ApiException{
        PurchaseOrder purchaseOrderToSave = new PurchaseOrder();

        purchaseOrderToSave = purchaseOrderRepository.save(purchaseOrderToSave);
        purchaseOrderToSave.setPurchaseOrderProducts(createPurchaseOrderProductsSet(purchaseOrderToSave, purchaseOrderDTO.getProducts()));
        purchaseOrderToSave.setDate(purchaseOrderDTO.getDate());
        purchaseOrderToSave.setStatus(purchaseStatusesRepository.findById(purchaseOrderDTO.getOrderStatus().getStatusCode()).orElseThrow());
        purchaseOrderToSave.setBuyer(buyerRepository.findById((purchaseOrderDTO.getBuyerId())).orElseThrow());

        return purchaseOrderRepository.save(purchaseOrderToSave);
    }

    @Override
    public PurchaseAmountDTO getAmountOfAnPurchaseOrder(PurchaseOrder purchaseOrder) {
        List<PurchaseOrderProducts> purchaseOrderProducts =  purchaseProductsRepository.findAllByPurchaseOrder(purchaseOrder);
        Double totalAmount = 0.0;

        for (PurchaseOrderProducts purchaseOrderProduct: purchaseOrderProducts) {
            totalAmount = totalAmount + purchaseOrderProduct.getProduct().getAmount() * purchaseOrderProduct.getQuantity();
        }

        return new PurchaseAmountDTO(totalAmount);
    }

    @Override
    public List<PurchaseOrderProductsResponse> getPurchaseOrderProducts(Long orderId) throws NotFoundApiException {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundApiException("Purchase order not found")
        );

        List<PurchaseOrderProducts> purchaseOrderProductsList = purchaseProductsRepository.findAllByPurchaseOrder(purchaseOrder);
        List<PurchaseOrderProductsResponse> productsResponse = createPurchaseOrderProductsResponseList(purchaseOrderProductsList);

        return productsResponse;
    }

    private List<PurchaseOrderProductsResponse> createPurchaseOrderProductsResponseList(List<PurchaseOrderProducts> purchaseOrderProductsList) {
        List<PurchaseOrderProductsResponse> productsResponse = new ArrayList<>();
        for (PurchaseOrderProducts elem: purchaseOrderProductsList) {
            Product product = elem.getProduct();
            productsResponse.add(new PurchaseOrderProductsResponse(product,elem));
        }
        return productsResponse;
    }

    public Set<PurchaseOrderProducts> createPurchaseOrderProductsSet(PurchaseOrder purchaseOrder, Set<PurchaseRequestProductsDTO> requestProductsDTO) throws ApiException {
        Set<PurchaseOrderProducts> purchaseOrderProductsSet = new HashSet<>();

        for (PurchaseRequestProductsDTO product: requestProductsDTO) {
            PurchaseOrderProducts purchaseOrderProducts = new PurchaseOrderProducts();
            purchaseOrderProducts.setPurchaseOrder(purchaseOrder);
            purchaseOrderProducts.setProduct(productRepository.findById(product.getProductId()).orElseThrow());
            purchaseOrderProducts.setQuantity(product.getQuantity());
            purchaseOrderProducts.setBatchId(updateProductQuantityFromBatch(product.getProductId(), product.getQuantity()));
            purchaseOrderProductsSet.add(purchaseProductsRepository.save(purchaseOrderProducts));
        }

        return purchaseOrderProductsSet;
    }

    public Long updateProductQuantityFromBatch(Long productId, Integer quantity) throws BadRequestApiException {

        Batch batch = batchRepository.getBatchByProductId(productId);

        if (!validator.hasDueDateEqualOrGreaterThanThreeWeeks(batch))
            throw new BadRequestApiException("Product due date less than three weeks");

        if (quantity > batch.getCurrentQuantity())
            throw new BadRequestApiException("No products left");

        batch.setLastQuantity(batch.getCurrentQuantity());
        batch.setCurrentQuantity(batch.getCurrentQuantity() - quantity);

        batchRepository.save(batch);

        return batch.getId();
    }
}
