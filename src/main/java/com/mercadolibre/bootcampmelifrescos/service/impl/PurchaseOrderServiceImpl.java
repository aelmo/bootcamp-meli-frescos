package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseRequestProductsDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.Cart;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrder;
import com.mercadolibre.bootcampmelifrescos.repository.*;
import com.mercadolibre.bootcampmelifrescos.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CartRepository cartRepository;
    private final PurchaseStatusesRepository purchaseStatusesRepository;
    private final ProductRepository productRepository;
    private final BatchRepository batchRepository;
    private final BuyerRepository buyerRepository;


    @Override
    public PurchaseAmountDTO createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws Exception{
        PurchaseOrder purchaseOrderToSave = new PurchaseOrder();
        Float total = 0.0F;

        Set<Product> products = new HashSet<>();

        for(PurchaseRequestProductsDTO product: purchaseOrderDTO.getProducts()) {
            Long productId = product.getProductId();
            Integer productQuantity = product.getQuantity();

            Float productPrice = productRepository.getOne(productId).getAmount();
            total = total + (productPrice * productQuantity);

            products.add(productRepository.getOne(productId));
            removeProductFromBatch(productId, productQuantity);
        }

        purchaseOrderToSave.setCart(saveCart(products));
        purchaseOrderToSave.setDate(purchaseOrderDTO.getDate());
        purchaseOrderToSave.setStatus(purchaseStatusesRepository.getOne(purchaseOrderDTO.getOrderStatus().getStatusCode()));
        purchaseOrderToSave.setBuyer(buyerRepository.getOne(purchaseOrderDTO.getBuyerId()));

        purchaseOrderRepository.save(purchaseOrderToSave);

        return new PurchaseAmountDTO(Double.valueOf(total));
    }


    public Cart saveCart(Set<Product> products) {
        Cart cart = new Cart();
        cart.setProducts(products);
        return cartRepository.save(cart);
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
