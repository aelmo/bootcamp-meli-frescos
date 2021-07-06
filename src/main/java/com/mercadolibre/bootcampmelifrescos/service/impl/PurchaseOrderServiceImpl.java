package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseRequestProductsDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.model.*;
import com.mercadolibre.bootcampmelifrescos.repository.*;
import com.mercadolibre.bootcampmelifrescos.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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


    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws Exception{
        PurchaseOrder purchaseOrderToSave = new PurchaseOrder();
        Float total = 0.0F;

        Set<Product> products = new HashSet<>();

        purchaseOrderToSave = purchaseOrderRepository.save(purchaseOrderToSave);
        purchaseOrderToSave.setPurchaseOrderProducts(createPurchaseOrderProductsSet(purchaseOrderToSave, purchaseOrderDTO.getProducts()));
        purchaseOrderToSave.setDate(purchaseOrderDTO.getDate());
        purchaseOrderToSave.setStatus(purchaseStatusesRepository.getOne(purchaseOrderDTO.getOrderStatus().getStatusCode()));
        purchaseOrderToSave.setBuyer(buyerRepository.getOne(purchaseOrderDTO.getBuyerId()));

        return purchaseOrderRepository.save(purchaseOrderToSave);
    }

    @Override
    public PurchaseAmountDTO getAmountOfAnPurchaseOrder(PurchaseOrder purchaseOrder) {
        List<PurchaseOrderProducts> purchaseOrderProducts =  purchaseProductsRepository.findAllBypurchaseOrder(purchaseOrder);
        Double totalAmount = 0.0;

        for (PurchaseOrderProducts purchaseOrderProduct: purchaseOrderProducts) {
            totalAmount = totalAmount + purchaseOrderProduct.getProduct().getAmount() * purchaseOrderProduct.getQuantity();
        }

        return new PurchaseAmountDTO(totalAmount);
    }

    public Set<PurchaseOrderProducts> createPurchaseOrderProductsSet(PurchaseOrder purchaseOrder, Set<PurchaseRequestProductsDTO> requestProductsDTO) throws Exception {
        Set<PurchaseOrderProducts> purchaseOrderProductsSet = new HashSet<>();

        for(PurchaseRequestProductsDTO product: requestProductsDTO) {
            removeProductFromBatch(product.getProductId(), product.getQuantity());

            PurchaseOrderProducts purchaseOrderProducts = new PurchaseOrderProducts();
            purchaseOrderProducts.setPurchaseOrder(purchaseOrder);
            purchaseOrderProducts.setProduct(productRepository.getOne(product.getProductId()));
            purchaseOrderProducts.setQuantity(product.getQuantity());
            purchaseOrderProductsSet.add(purchaseProductsRepository.save(purchaseOrderProducts));
        }

        return purchaseOrderProductsSet;
    }

    public void removeProductFromBatch(Long productId, Integer quantity) throws Exception {
        try {
            Batch batch = batchRepository.getBatchByProductId(productId);
            if(quantity > batch.getCurrentQuantity()) {
                throw new Exception();
            }
            batch.setCurrentQuantity(batch.getCurrentQuantity() - quantity);
            batchRepository.save(batch);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
