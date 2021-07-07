package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.response.ProductBatchResponse;
import com.mercadolibre.bootcampmelifrescos.model.*;
import com.mercadolibre.bootcampmelifrescos.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BatchServiceTest {

    @Autowired
    private BatchService subject;

    @MockBean
    BatchRepository batchRepository;

    @MockBean
    InboundOrder inboundOrder;

    @BeforeEach
    void setup() {
        Section section = new Section(1L, new Warehouse(), new Category());

        Batch batch1 = new Batch(
                1L,
                18.0F,
                8.5F,
                100,
                100,
                LocalDate.of(2021, 06, 27),
                LocalDateTime.of(2021, 06, 27, 01,01),
                LocalDate.of(2022, 06, 27),
                new Product(),
                inboundOrder);

        Batch batch2 = new Batch(
                1L,
                18.0F,
                8.5F,
                10,
                10,
                LocalDate.of(2021, 6, 27),
                LocalDateTime.of(2021, 6, 27, 01,01),
                LocalDate.of(2022, 7, 27),
                new Product(),
                inboundOrder);

        Batch batch3 = new Batch(
                1L,
                18.0F,
                8.5F,
                70,
                70,
                LocalDate.of(2021, 6, 27),
                LocalDateTime.of(2021, 6, 27, 01,01),
                LocalDate.of(2022, 5, 27),
                new Product(),
                inboundOrder);

        List<Batch> batchList = List.of(batch1, batch2, batch3);

        when(batchRepository.findAllByProductId(any())).thenReturn(batchList);
        when(inboundOrder.getSection()).thenReturn(section);
    }

    @Test
    void shouldReturnAllBatches() throws Exception {
        ProductBatchResponse result = subject.getAllBatches(1L, "");

        assertThat(result.getBatchStock().size()).isEqualTo(3);
    }

    @Test
    void shouldReturnAllBatchesOrderedByCurrentQuantity() throws Exception {
        ProductBatchResponse result = subject.getAllBatches(1L, "C");

        assertThat(result.getBatchStock().size()).isEqualTo(3);
        assertThat(result.getBatchStock().get(0).getCurrentQuantity()).isEqualTo(10);
        assertThat(result.getBatchStock().get(1).getCurrentQuantity()).isEqualTo(70);
        assertThat(result.getBatchStock().get(2).getCurrentQuantity()).isEqualTo(100);
    }

    @Test
    void shouldReturnAllBatchesOrderedByDueDate() throws Exception {
        ProductBatchResponse result = subject.getAllBatches(1L, "F");

        assertThat(result.getBatchStock().size()).isEqualTo(3);
        assertThat(result.getBatchStock().get(0).getDueDate()).isEqualTo(LocalDate.of(2022, 5, 27));
        assertThat(result.getBatchStock().get(1).getDueDate()).isEqualTo(LocalDate.of(2022, 6, 27));
        assertThat(result.getBatchStock().get(2).getDueDate()).isEqualTo(LocalDate.of(2022, 7, 27));
    }
}
