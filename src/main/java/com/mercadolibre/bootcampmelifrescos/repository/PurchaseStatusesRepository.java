package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.model.PurchaseStatuses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseStatusesRepository extends JpaRepository<PurchaseStatuses, Long> {
}
