package com.mercadolibre.bootcampmelifrescos.services;

import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.model.Category;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Seller;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.service.ProductService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @Disabled
    void shouldReturnAllProducts() throws Exception {
        //arrange
        Category category = new Category(1L, "FR", "Frozen");
        Product firstProduct = new Product(1L,"Uva",new Seller(),category);
        Product secondProduct = new Product(2L,"Pessego",new Seller(),category);
        ProductDTO expectedFirstProduct = new ProductDTO("Uva","Frozen");
        ProductDTO expectedSecondProduct = new ProductDTO("Pessego","Frozen");
        List<Product> listWithProducts = new ArrayList<>();
        listWithProducts.add(firstProduct);
        listWithProducts.add(secondProduct);

        //act
        when(productRepository.findAll()).thenReturn(listWithProducts);
        List<ProductDTO> result = productService.getAllProducts();

        //assert
        assertThat(result).contains(expectedFirstProduct);
        assertThat(result).contains(expectedSecondProduct);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void shouldThrownExceptionWhenThereIsNoProducts(){
        when(productRepository.findAll()).thenReturn(List.of());
        assertThrows(Exception.class,() -> productService.getAllProducts());
    }
}
