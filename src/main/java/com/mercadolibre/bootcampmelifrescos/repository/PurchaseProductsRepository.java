package com.mercadolibre.bootcampmelifrescos.repository;

import com.mercadolibre.bootcampmelifrescos.embeddables.CartProductPK;
import com.mercadolibre.bootcampmelifrescos.model.Cart;
import com.mercadolibre.bootcampmelifrescos.model.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductsRepository extends JpaRepository<CartProducts, CartProductPK> {
}
