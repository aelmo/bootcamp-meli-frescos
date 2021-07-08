package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch,Long> {
    List<Batch> findAllByProductId(Long productId);

    @Query(value = "SELECT * FROM batch WHERE batch.product_id = :product_id AND batch.current_quantity > 0 ORDER BY batch.current_quantity DESC LIMIT 1", nativeQuery = true)
    Batch getBatchByProductId(@Param("product_id") Long productId);
}
