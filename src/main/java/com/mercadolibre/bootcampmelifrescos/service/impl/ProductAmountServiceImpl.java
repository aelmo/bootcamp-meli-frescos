package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.response.ProductAmountResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.repository.CategoryRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.service.ProductAmountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductAmountServiceImpl implements ProductAmountService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductAmountResponse> getAllProductsWithAmount(String order) throws ApiException {
        List<Product> products;

        if (order.equalsIgnoreCase("asc"))
            products = productRepository.findAllByOrderByAmountAsc();
        else if (order.equalsIgnoreCase("desc"))
            products = productRepository.findAllByOrderByAmountDesc();
        else
            products = productRepository.findAll();


        if (products.isEmpty())
            throw new NotFoundApiException("There is no products available");

        List<ProductAmountResponse> productsResponse = convertProductListToResponse(products);

        return productsResponse;
    }

    @Override
    public List<ProductAmountResponse> getProductsByCategoryWithAmount(String categoryCode, String order) throws ApiException {
        categoryRepository.findByCode(categoryCode).orElseThrow(
                ()-> new NotFoundApiException("Category " + categoryCode + " not found")
        );

        List<Product> products;

        if (order.equalsIgnoreCase("asc"))
            products = productRepository.findAllByCategoryCodeOrderByAmountAsc(categoryCode);
        else if (order.equalsIgnoreCase("desc"))
            products = productRepository.findAllByCategoryCodeOrderByAmountDesc(categoryCode);
        else
            products = productRepository.findAllByCategoryCode(categoryCode);

        if (products.isEmpty())
            throw new NotFoundApiException("There is no products available to this category");

        List<ProductAmountResponse> productsResponse = convertProductListToResponse(products);

        return productsResponse;

    }

    private List<ProductAmountResponse> convertProductListToResponse(List<Product> productList) {
        List<ProductAmountResponse> productsResponseList = new ArrayList<>();

        for (Product product : productList) {
            productsResponseList.add(new ProductAmountResponse(product));
        }
        return productsResponseList;
    }
}

