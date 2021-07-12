package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.TopProductResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.repository.CategoryRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    public List<ProductDTO> getAllProducts() throws ApiException {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty())
            throw new NotFoundApiException("There is no products available");

        List<ProductDTO> productsResponseList = convertProductListToResponseList(products);

        return productsResponseList;
    }

    public List<ProductDTO> getProductsByCategory(String categoryCode) throws ApiException {
        categoryRepository.findByCode(categoryCode).orElseThrow(
                ()-> new NotFoundApiException("Category " + categoryCode + " not found")
        );

        List<Product> products = productRepository.findAllByCategoryCode(categoryCode);

        if (products.isEmpty())
            throw new NotFoundApiException("There is no products available to this category");

        List<ProductDTO> productResponseList = convertProductListToResponseList(products);

        return productResponseList;
    }

    @Override
    public List<TopProductResponse> getTopProducts(int topQuantity) throws ApiException{
        List<TopProductResponse> products = productRepository.findTopByQuantitySold();
        if(products.isEmpty())
            throw new NotFoundApiException("No products sold so far");

        return products.stream().limit(topQuantity).collect(Collectors.toList());
    }

    private List<ProductDTO> convertProductListToResponseList(List<Product> productList) {
        List<ProductDTO> productsResponseList = new ArrayList<>();

        for (Product product : productList) {
            productsResponseList.add(new ProductDTO(product));
        }
        return productsResponseList;
    }
}
