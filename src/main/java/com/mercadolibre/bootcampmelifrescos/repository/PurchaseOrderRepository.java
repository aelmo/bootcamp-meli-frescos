package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
    @Query(value = "select id from purchase_order where purchase_order.buyer_id != \"NULL\" AND purchase_order.date > :startDate AND purchase_order.date < :endDate", nativeQuery = true)
    List<Integer> getAllPurchaseOrderByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
