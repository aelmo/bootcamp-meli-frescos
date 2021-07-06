package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrder;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseProductsRepository extends JpaRepository<PurchaseOrderProducts, Long> {
    List<PurchaseOrderProducts> findAllBypurchaseOrder(PurchaseOrder purchaseOrder);
}
