package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Category;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.repository.CategoryRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts() throws ApiException {
        List<Product> products = productRepository.findAll();

        if(products.isEmpty())
            throw new NotFoundApiException("There is no products available");

        List<ProductDTO> productsResponseList = convertProductListToResponseList(products);

        return productsResponseList;
    }

    public List<ProductDTO> getProductsByCategory(String categoryCode) throws ApiException {
        categoryRepository.findByCode(categoryCode).orElseThrow(
                ()-> new NotFoundApiException("Category " + categoryCode + " not found")
        );

        List<Product> products = productRepository.findAllByCategoryCode(categoryCode);

        if(products.isEmpty())
            throw new NotFoundApiException("There is no products available to this category");

        List<ProductDTO> productResponseList = convertProductListToResponseList(products);

        return productResponseList;
    }

    private List<ProductDTO> convertProductListToResponseList(List<Product> productList) {
        List<ProductDTO> productsResponseList = new ArrayList<>();

        for(Product product : productList){
            productsResponseList.add(new ProductDTO(product));
        }
        return productsResponseList;
    }
}
