package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseRequestProductsRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountResponse;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseOrderProductsResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.BadRequestApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
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
    public PurchaseAmountResponse updatePurchaseOrder(PurchaseOrderRequest purchaseOrderRequest, Long orderId) throws ApiException {
        if (purchaseOrderRepository.findById(orderId).isEmpty())
            throw new NotFoundApiException("Purchase order not found");
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundApiException("Purchase order not found")
        );
        List<PurchaseOrderProducts> purchaseOrderProducts = purchaseProductsRepository.findAllByPurchaseOrder(purchaseOrder);

        for (PurchaseOrderProducts purchaseOrderProducts1: purchaseOrderProducts) {
            if (batchRepository.findById(purchaseOrderProducts1.getBatchId()).isEmpty()) {
                throw new NotFoundApiException("Batch not found");
            }
            Batch batch = batchRepository.findById(purchaseOrderProducts1.getBatchId()).orElseThrow(
                    () -> new NotFoundApiException("Batch not found")
            );
            batch.setCurrentQuantity(batch.getLastQuantity());
            batchRepository.save(batch);
        }

        purchaseProductsRepository.deleteAll(purchaseOrderProducts);

        createPurchaseOrderProductsSet(purchaseOrder, purchaseOrderRequest.getProducts());

        purchaseOrder.setDate(purchaseOrderRequest.getDate());
        purchaseOrder.setStatus(purchaseStatusesRepository.findById(purchaseOrderRequest.getOrderStatus().getStatusCode()).orElseThrow(
                () -> new NotFoundApiException("Status not found")
        ));
        purchaseOrder.setBuyer(buyerRepository.findById(purchaseOrderRequest.getBuyerId()).orElseThrow(
                () -> new NotFoundApiException("Buyer not found")
        ));

        return getAmountOfAnPurchaseOrder(purchaseOrderRepository.save(purchaseOrder));
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest) throws ApiException{
        PurchaseOrder purchaseOrderToSave = new PurchaseOrder();

        purchaseOrderToSave = purchaseOrderRepository.save(purchaseOrderToSave);
        purchaseOrderToSave.setPurchaseOrderProducts(createPurchaseOrderProductsSet(purchaseOrderToSave, purchaseOrderRequest.getProducts()));
        purchaseOrderToSave.setDate(purchaseOrderRequest.getDate());
        purchaseOrderToSave.setStatus(purchaseStatusesRepository.findById(purchaseOrderRequest.getOrderStatus().getStatusCode()).orElseThrow(
                () -> new NotFoundApiException("Status not found")
        ));
        purchaseOrderToSave.setBuyer(buyerRepository.findById((purchaseOrderRequest.getBuyerId())).orElseThrow(
                () -> new NotFoundApiException("Buyer not found")
        ));

        return purchaseOrderRepository.save(purchaseOrderToSave);
    }

    @Override
    public PurchaseAmountResponse getAmountOfAnPurchaseOrder(PurchaseOrder purchaseOrder) {
        List<PurchaseOrderProducts> purchaseOrderProducts =  purchaseProductsRepository.findAllByPurchaseOrder(purchaseOrder);
        Double totalAmount = 0.0;

        for (PurchaseOrderProducts purchaseOrderProduct: purchaseOrderProducts) {
            totalAmount = totalAmount + purchaseOrderProduct.getProduct().getAmount() * purchaseOrderProduct.getQuantity();
        }

        return new PurchaseAmountResponse(totalAmount);
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

    public Set<PurchaseOrderProducts> createPurchaseOrderProductsSet(PurchaseOrder purchaseOrder, Set<PurchaseRequestProductsRequest> requestProductsDTO) throws ApiException {
        Set<PurchaseOrderProducts> purchaseOrderProductsSet = new HashSet<>();

        for (PurchaseRequestProductsRequest product: requestProductsDTO) {
            PurchaseOrderProducts purchaseOrderProducts = new PurchaseOrderProducts();
            purchaseOrderProducts.setPurchaseOrder(purchaseOrder);
            purchaseOrderProducts.setProduct(productRepository.findById(product.getProductId()).orElseThrow(
                    () -> new NotFoundApiException("Product not found")
            ));
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
