package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.response.ProductAmountResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;

import java.util.List;

public interface ProductAmountService {
    List<ProductAmountResponse> getAllProductsWithAmount(String order) throws ApiException;

    List<ProductAmountResponse> getProductsByCategoryWithAmount(String categoryCode, String order) throws ApiException;
}
