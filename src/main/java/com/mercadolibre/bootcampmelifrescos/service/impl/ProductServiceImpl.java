package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.MostSoldProductAndQuantityDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.MostSoldProduct;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.repository.CategoryRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.repository.PurchaseProductsRepository;
import com.mercadolibre.bootcampmelifrescos.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PurchaseProductsRepository purchaseProductsRepository;

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
    public MostSoldProduct getMostSoldProduct(LocalDate startDate, LocalDate endDate) throws Exception {
        Map<String, Integer> mostSoldProduct = purchaseProductsRepository.getMostSoldProductIdAndQuantity(startDate, endDate);
        if(mostSoldProduct.isEmpty()) {
            throw new Exception("Nenhum produto vendido");
        }
        MostSoldProduct response = new MostSoldProduct();
        Product product = productRepository.findById(Long.parseLong(String.valueOf(mostSoldProduct.get("productId")))).orElseThrow(() -> new Exception("Product not founded"));

        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setSoldQty(Integer.valueOf(String.valueOf(mostSoldProduct.get("quantity"))));

        return response;
    }

    private List<ProductDTO> convertProductListToResponseList(List<Product> productList) {
        List<ProductDTO> productsResponseList = new ArrayList<>();

        for (Product product : productList) {
            productsResponseList.add(new ProductDTO(product));
        }
        return productsResponseList;
    }
}
