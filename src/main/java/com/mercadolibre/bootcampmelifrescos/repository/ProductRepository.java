package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.dtos.BestSellerDTO;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p left join Category c on p.category.id = c.id where c.code = :categoryCode")
    List<Product> findAllByCategoryCode(String categoryCode);

    @Query(value = "SELECT p.id as productId, p.name as productName, sum(pp.quantity) as quantity FROM product p inner join purchase_products pp on p.id = pp.product_id " +
            "group by p.id order by sum(pp.quantity) desc limit :numberOfProducts", nativeQuery = true)
    List<BestSellerDTO> findBestSellers(int numberOfProducts);
}
