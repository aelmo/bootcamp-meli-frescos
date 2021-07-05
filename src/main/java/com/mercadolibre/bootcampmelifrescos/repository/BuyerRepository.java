package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
