package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.SectionDTO;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;
import com.mercadolibre.bootcampmelifrescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcampmelifrescos.service.impl.InboundOrderConverterImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
public class InboundOrderServiceTest {

    @Autowired
    private InboundOrderService subject;

    @MockBean
    Validator validator;

    @MockBean
    InboundOrderRepository inboundOrderRepository;

    @MockBean
    InboundOrderConverterImpl inboundConverter;

    @Test
    void shouldValidateCategoryAndSaveInboundOrder() throws Exception {
        SectionDTO sectionDTO = new SectionDTO(3L, 1L);
        BatchDTO batchDTO = new BatchDTO( 1L, 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8));
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(1L, LocalDate.now(), sectionDTO, List.of(batchDTO));
        when(inboundConverter.dtoToEntity(any())).thenReturn(new InboundOrder());

        subject.createInboundOrder(inboundOrderDTO);

        verify(validator, times(1)).validateCategorySection(any(), any());
        verify(inboundOrderRepository, times(1)).save(any());
    }

    @Test
    void shouldNotSaveInboundOrderIfCategoryIsInvalid() throws Exception {
        SectionDTO sectionDTO = new SectionDTO(3L, 1L);
        BatchDTO batchDTO = new BatchDTO( 1L, 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8));
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(1L, LocalDate.now(), sectionDTO, List.of(batchDTO));
        when(inboundConverter.dtoToEntity(any())).thenReturn(new InboundOrder());
        doThrow(new Exception()).when(validator).validateCategorySection(any(), any());

        assertThrows(Exception.class, () -> subject.createInboundOrder(any()));

        verify(validator, times(1)).validateCategorySection(any(), any());
        verify(inboundOrderRepository, times(0)).save(any());
    }
}
