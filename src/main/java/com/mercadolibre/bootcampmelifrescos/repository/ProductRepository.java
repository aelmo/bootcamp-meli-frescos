package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p left join Category c on p.category.id = c.id where c.code = :categoryCode")
    List<Product> findAllByCategoryCode(String categoryCode);

    List<Product> findAllByCategoryCodeOrderByAmountAsc(String categoryCode);
    List<Product> findAllByCategoryCodeOrderByAmountDesc(String categoryCode);

    List<Product> findAllByOrderByAmountAsc();
    List<Product> findAllByOrderByAmountDesc();
}
