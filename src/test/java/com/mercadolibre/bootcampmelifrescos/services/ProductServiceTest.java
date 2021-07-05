package com.mercadolibre.bootcampmelifrescos.services;

import com.mercadolibre.bootcampmelifrescos.model.Category;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Seller;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void shouldReturnAllProductsAndHTTPStatusCodeOK(){
        //given
        Category category = new Category(1L, "FR", "Frozen");
        Product firstProduct = new Product(1L,"Uva",new Seller(),category);
        Product secondProduct = new Product(2L,"Pessego",new Seller(),category);

        List<Product> listWithProducts = new ArrayList<>();
        listWithProducts.add(firstProduct);
        listWithProducts.add(secondProduct);

        //when
        when(productRepository.findAll()).thenReturn(listWithProducts);

        ResponseEntity result = productService.getAllProducts();

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @Disabled
    void shouldReturn404CodeWhenThereIsNoProduct(){

    }
}
