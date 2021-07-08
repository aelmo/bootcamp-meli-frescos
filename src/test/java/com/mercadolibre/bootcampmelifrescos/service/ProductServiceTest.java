package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Category;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Seller;
import com.mercadolibre.bootcampmelifrescos.repository.CategoryRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void shouldReturnAllProducts() throws Exception {
        //arrange
        ProductDTO expectedFirstProduct = new ProductDTO("Uva","Frozen");
        ProductDTO expectedSecondProduct = new ProductDTO("Pessego","Frozen");
        List<Product> listWithProducts = createListOfProducts();

        //act
        when(productRepository.findAll()).thenReturn(listWithProducts);
        List<ProductDTO> result = productService.getAllProducts();

        //assert
        assertThat(result).contains(expectedFirstProduct);
        assertThat(result).contains(expectedSecondProduct);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void shouldThrowExceptionWhenThereIsNoProducts(){
        when(productRepository.findAll()).thenReturn(List.of());
        assertThrows(NotFoundApiException.class,() -> productService.getAllProducts());
    }

    @Test
    void shouldReturnAllProductsByCategory() throws ApiException {
        //arrange
        List<Product> productList = createListOfProducts();
        ProductDTO expectedFirstProduct = new ProductDTO("Uva","Frozen");
        ProductDTO expectedSecondProduct = new ProductDTO("Pessego","Frozen");

        //act
        when(productRepository.findAllByCategoryCode("FR")).thenReturn(productList);
        when(categoryRepository.findByCode("FR")).thenReturn(Optional.of(new Category()));
        List<ProductDTO> result = productService.getProductsByCategory("FR");

        //assert
        assertThat(result).contains(expectedFirstProduct);
        assertThat(result).contains(expectedSecondProduct);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void shouldThrowExceptionWhenThereIsNoProductsAvailableInCategory() throws ApiException {
        when(productRepository.findAllByCategoryCode("RF")).thenReturn(List.of());
        assertThrows(NotFoundApiException.class,() -> productService.getProductsByCategory("RF"));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound(){
        when(categoryRepository.findByCode("FRD")).thenReturn(empty());
        assertThrows(NotFoundApiException.class,()-> productService.getProductsByCategory("FRD"));
    }

    private List<Product> createListOfProducts(){
        Category category = new Category(1L, "FR", "Frozen");
        Product firstProduct = new Product(1L,"Uva",new Seller(),category);
        Product secondProduct = new Product(2L,"Pessego",new Seller(),category);
        List<Product> listWithProducts = new ArrayList<>();
        listWithProducts.add(firstProduct);
        listWithProducts.add(secondProduct);

        return listWithProducts;
    }

}
