package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.dtos.MostSoldProductAndQuantityDTO;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrder;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrderProducts;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PurchaseProductsRepository extends JpaRepository<PurchaseOrderProducts, Long> {

    List<PurchaseOrderProducts> findAllByPurchaseOrder(PurchaseOrder purchaseOrder);

    @Query(value = "SELECT purchase_products.product_id as productId, SUM(purchase_products.quantity) as quantity\n" +
            "from purchase_products\n" +
            "INNER JOIN purchase_order\n" +
            "ON purchase_products.purchase_order_id = purchase_order.id\n" +
            "WHERE purchase_order.date > :startDate\n" +
            "AND purchase_order.date < :endDate\n" +
            "GROUP BY purchase_products.product_id\n" +
            "ORDER BY quantity\n" +
            "DESC LIMIT 1;", nativeQuery = true)
    Map<String, Integer> getMostSoldProductIdAndQuantity(LocalDate startDate, LocalDate endDate);

}
