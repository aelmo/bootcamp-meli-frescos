package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.Category;
import org.springframework.data.domain.Sort;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch,Long> {

    List<Batch> findAllByProductId(Long productId);

    @Query(value = "SELECT * FROM batch WHERE batch.product_id = :product_id AND batch.current_quantity > 0 ORDER BY batch.current_quantity DESC LIMIT 1", nativeQuery = true)
    Batch getBatchByProductId(@Param("product_id") Long productId);

    @Query(value = "SELECT b FROM Batch b WHERE " +
            "b.dueDate <= :date AND (:category IS NULL OR b.product.category = :category)")
    List<Batch> findAllByDueDateIsLessThanEqualAndOptionalProductCategoryOrderByDueDate(
            LocalDate date,
            Optional<Category> category,
            Sort sort
    );

    List<Batch> findAllByProductOrderByInboundOrder(Product product);
}

