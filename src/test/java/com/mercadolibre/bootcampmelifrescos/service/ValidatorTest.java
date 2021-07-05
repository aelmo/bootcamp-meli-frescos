package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ValidatorTest {

    @Autowired
    Validator subject;

    @Test
    void shouldThrowExceptionWhenCategoryIsInvalid() throws Exception {
        Category category = new Category(1L, "RR", "Refrigerated");
        Category differentCategory = new Category();
        Section section = new Section(1L, new Warehouse(), category);
        Product product = new Product(1L, "Product", new Seller(), differentCategory);
        Batch batch = new Batch( 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8),
                product, new InboundOrder());
        Set<Batch> batchSet = Set.of(batch);

        assertThrows(Exception.class, () -> subject.validateCategorySection(section, batchSet));
    }

    @Test
    void shouldNotThrowExceptionWhenCategoryIsValid() throws Exception {
        Category category = new Category(1L, "RR", "Refrigerated");
        Section section = new Section(1L, new Warehouse(), category);
        Product product = new Product(1L, "Product", new Seller(), category);
        Batch batch = new Batch( 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8),
                product, new InboundOrder());
        Set<Batch> batchSet = Set.of(batch);

        assertDoesNotThrow(() -> subject.validateCategorySection(section, batchSet));
    }
}