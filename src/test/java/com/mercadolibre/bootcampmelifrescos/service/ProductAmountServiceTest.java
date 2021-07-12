package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.response.ProductAmountResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
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
import java.util.List;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductAmountServiceTest {

    @Autowired
    private ProductAmountService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void shouldReturnAllProductsWithAmountByDescOrder() throws Exception {
        //arrange
        ProductAmountResponse expectedFirstProduct = new ProductAmountResponse("Uva","Frozen", 10F);
        ProductAmountResponse expectedSecondProduct = new ProductAmountResponse("Pessego","Frozen", 8F);
        ProductAmountResponse expectedThirdProduct = new ProductAmountResponse("Batata","Frozen", 3F);

        //act
        when(productRepository.findAllByOrderByAmountDesc()).thenReturn(createListOfProducts());
        List<ProductAmountResponse> result = productService.getAllProductsWithAmount("desc");


        //assert
        assertThat(result).contains(expectedFirstProduct);
        assertThat(result).contains(expectedSecondProduct);
        assertThat(result).contains(expectedThirdProduct);
        assertThat(result).first().isEqualTo(expectedFirstProduct);
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound(){
        when(categoryRepository.findByCode("FRD")).thenReturn(empty());
        assertThrows(NotFoundApiException.class,()-> productService.getProductsByCategoryWithAmount("FRD", null));
    }


    private List<Product> createListOfProducts(){
        Category category = new Category(1L, "FR", "Frozen");
        Product firstProduct = new Product(1L,"Uva",new Seller(),category, 10F);
        Product secondProduct = new Product(2L,"Pessego",new Seller(),category, 8F);
        Product thirdProduct = new Product(2L,"Batata",new Seller(),category, 3F);
        List<Product> listWithProducts = new ArrayList<>();
        listWithProducts.add(firstProduct);
        listWithProducts.add(secondProduct);
        listWithProducts.add(thirdProduct);

        return listWithProducts;
    }

}
