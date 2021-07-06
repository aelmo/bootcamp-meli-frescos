package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch,Long> {
    List<Batch> findAllByInboundOrder(InboundOrder inboundOrder);
}
