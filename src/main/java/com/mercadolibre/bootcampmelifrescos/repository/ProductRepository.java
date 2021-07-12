package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.dtos.response.TopProductResponse;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p left join Category c on p.category.id = c.id where c.code = :categoryCode")
    List<Product> findAllByCategoryCode(String categoryCode);

    @Query(value = "SELECT new com.mercadolibre.bootcampmelifrescos.dtos.response.TopProductResponse(p.name,sum(purOrd.quantity)) FROM Product p inner join PurchaseOrderProducts purOrd on p.id = purOrd.product.id group by p ORDER BY sum(purOrd.quantity) DESC ")
    List<TopProductResponse> findTopByQuantitySold();
}
