package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.SectionDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.repository.SectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@SpringBootTest
public class InboundOrderConverterTest {

    @Autowired
    InboundOrderConverter subject;

    @MockBean
    SectionRepository sectionRepository;

    @MockBean
    ProductRepository productRepository;

    @Test
    void shouldThrowExceptionIfSectionDoesNotExist() {
        SectionDTO sectionDTO = new SectionDTO(3L, 1L);
        BatchDTO batchDTO = new BatchDTO( 1L, 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8));
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(1L, LocalDate.now(), sectionDTO, List.of(batchDTO));
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
        when(sectionRepository.findById(any())).thenReturn(empty());

        assertThrows(NotFoundApiException.class, () -> subject.dtoToEntity(inboundOrderDTO));
    }

    @Test
    void shouldThrowExceptionIfProductDoesNotExist() {
        SectionDTO sectionDTO = new SectionDTO(3L, 1L);
        BatchDTO batchDTO = new BatchDTO( 1L, 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8));
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(1L, LocalDate.now(), sectionDTO, List.of(batchDTO));
        when(productRepository.findById(any())).thenReturn(empty());
        when(sectionRepository.findById(any())).thenReturn(Optional.of(new Section()));

        assertThrows(NotFoundApiException.class, () -> subject.dtoToEntity(inboundOrderDTO));
    }

}
