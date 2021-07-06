package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseRequestProductsDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.model.*;
import com.mercadolibre.bootcampmelifrescos.repository.*;
import com.mercadolibre.bootcampmelifrescos.service.PurchaseOrderService;
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


    @Override
    public PurchaseAmountDTO updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Long orderId) throws Exception {
        if (!purchaseOrderRepository.findById(orderId).isPresent())
            throw new Exception("Purchase order not found");
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(orderId);
        List<PurchaseOrderProducts> purchaseOrderProducts = purchaseProductsRepository.findAllByPurchaseOrder(purchaseOrder);
        Set<PurchaseOrderProducts> purchaseOrderProductsTemporary = new HashSet<>();

        for (PurchaseRequestProductsDTO product : purchaseOrderDTO.getProducts()) {
            PurchaseOrderProducts purchaseOrderProductsList = new PurchaseOrderProducts();
            purchaseOrderProductsList.setPurchaseOrder(purchaseOrder);
            purchaseOrderProductsList.setProduct(productRepository.getOne(product.getProductId()));
            purchaseOrderProductsList.setQuantity(product.getQuantity());
            purchaseOrderProductsTemporary.add(purchaseOrderProductsList);
        }

        for(PurchaseOrderProducts purchaseOrderProductsToCompare : purchaseOrderProductsTemporary){
            Product product = purchaseOrderProductsToCompare.getProduct();
            PurchaseOrderProducts purchaseOrderProducts1 = new PurchaseOrderProducts();
            for(PurchaseOrderProducts purchaseOrderProducts2 : purchaseOrderProducts){
                if(purchaseOrderProducts2.getProduct().getId()==product.getId()){
                    purchaseOrderProducts1=purchaseOrderProducts2;
                    break;
                }
            }
            Integer productQuantity = purchaseOrderProductsToCompare.getQuantity()-purchaseOrderProducts1.getQuantity();
            updateProductQuantityFromBatch(product.getId(), productQuantity);
            purchaseOrderProducts1.setProduct(purchaseOrderProductsToCompare.getProduct());
            purchaseOrderProducts1.setQuantity(purchaseOrderProductsToCompare.getQuantity());
            purchaseProductsRepository.save(purchaseOrderProducts1);
        }

        //purchaseOrderProducts=purchaseOrderProductsTemporary;
        //purchaseProductsRepository.saveAll(purchaseOrderProducts);

        PurchaseOrder purchaseOrderToUpdate = new PurchaseOrder();
        purchaseOrderToUpdate.setPurchaseOrderProducts(Set.copyOf(purchaseOrderProducts));
        purchaseOrderToUpdate.setDate(purchaseOrderDTO.getDate());
        purchaseOrderToUpdate.setStatus(purchaseStatusesRepository.getOne(purchaseOrderDTO.getOrderStatus().getStatusCode()));
        purchaseOrderToUpdate.setBuyer(buyerRepository.getOne(purchaseOrderDTO.getBuyerId()));

        purchaseOrderRepository.save(purchaseOrderToUpdate);

        return getAmountOfAnPurchaseOrder(purchaseOrder);
    }

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
        List<PurchaseOrderProducts> purchaseOrderProducts =  purchaseProductsRepository.findAllByPurchaseOrder(purchaseOrder);
        Double totalAmount = 0.0;

        for (PurchaseOrderProducts purchaseOrderProduct: purchaseOrderProducts) {
            totalAmount = totalAmount + purchaseOrderProduct.getProduct().getAmount() * purchaseOrderProduct.getQuantity();
        }

        return new PurchaseAmountDTO(totalAmount);
    }

    public Set<PurchaseOrderProducts> createPurchaseOrderProductsSet(PurchaseOrder purchaseOrder, Set<PurchaseRequestProductsDTO> requestProductsDTO) throws Exception {
        Set<PurchaseOrderProducts> purchaseOrderProductsSet = new HashSet<>();

        for(PurchaseRequestProductsDTO product: requestProductsDTO) {
            updateProductQuantityFromBatch(product.getProductId(), product.getQuantity());

            PurchaseOrderProducts purchaseOrderProducts = new PurchaseOrderProducts();
            purchaseOrderProducts.setPurchaseOrder(purchaseOrder);
            purchaseOrderProducts.setProduct(productRepository.getOne(product.getProductId()));
            purchaseOrderProducts.setQuantity(product.getQuantity());
            purchaseOrderProductsSet.add(purchaseProductsRepository.save(purchaseOrderProducts));
        }

        return purchaseOrderProductsSet;
    }

    public void updateProductQuantityFromBatch(Long productId, Integer quantity) throws Exception {
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
