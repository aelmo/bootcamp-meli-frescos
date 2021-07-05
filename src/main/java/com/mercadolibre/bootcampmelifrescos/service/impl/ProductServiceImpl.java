package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.model.Product;
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

    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<Product> allProductsList = productRepository.findAll();

        if(allProductsList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<ProductDTO> productsResponseList = new ArrayList<>();

        for(Product product : allProductsList){
            productsResponseList.add(new ProductDTO(product));
        }

        return new ResponseEntity<>(productsResponseList, HttpStatus.OK);
    }
}
