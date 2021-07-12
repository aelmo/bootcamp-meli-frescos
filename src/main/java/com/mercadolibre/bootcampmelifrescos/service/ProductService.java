package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts() throws ApiException;

    List<ProductDTO> getProductsByCategory(String categoryCode) throws ApiException;
}
