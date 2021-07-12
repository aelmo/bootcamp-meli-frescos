package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.repository.CategoryRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.repository.SellerRepository;
import com.mercadolibre.bootcampmelifrescos.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;

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

        return convertProductListToResponseList(products);
    }

    @Override
    public List<ProductDTO> getProductsBySeller(final Long sellerId) throws ApiException {
        sellerRepository.findById(sellerId).orElseThrow(
                () -> new NotFoundApiException("Seller " + sellerId + " not found.")
        );

        List<Product> products = productRepository.findAllBySeller(sellerRepository.getById(sellerId));

        if (products.isEmpty())
            throw new NotFoundApiException("There are no products for this seller.");

        return convertProductListToResponseList(products);
    }

    private List<ProductDTO> convertProductListToResponseList(List<Product> productList) {
        List<ProductDTO> productsResponseList = new ArrayList<>();

        for (Product product : productList) {
            productsResponseList.add(new ProductDTO(product));
        }
        return productsResponseList;
    }
}
