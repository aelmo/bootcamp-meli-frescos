package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.SectionDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcampmelifrescos.service.impl.InboundOrderConverterImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    void whenCreating_shouldValidateCategoryAndSaveInboundOrder() throws Exception {
        SectionDTO sectionDTO = new SectionDTO(3L, 1L);
        BatchDTO batchDTO = new BatchDTO( 1L, 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8));
        Batch batch = new Batch(batchDTO, new Product());
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(1L, LocalDate.now(), sectionDTO, List.of(batchDTO));
        InboundOrder inboundOrder = new InboundOrder(1L,
                LocalDate.of(2021, 06, 27),
                new Section(),
                Set.of(batch),
                null
        );
        when(inboundConverter.dtoToEntity(any())).thenReturn(inboundOrder);

        subject.createInboundOrder(inboundOrderDTO);

        verify(validator, times(1)).validateCategorySection(any(), any());
        verify(inboundOrderRepository, times(1)).save(any());
    }

    @Test
    void whenCreating_shouldNotSaveInboundOrderIfCategoryIsInvalid() throws Exception {
        SectionDTO sectionDTO = new SectionDTO(3L, 1L);
        BatchDTO batchDTO = new BatchDTO( 1L, 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8));
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(1L, LocalDate.now(), sectionDTO, List.of(batchDTO));
        when(inboundConverter.dtoToEntity(any())).thenReturn(new InboundOrder());
        doThrow(new ApiException()).when(validator).validateCategorySection(any(), any());

        assertThrows(ApiException.class, () -> subject.createInboundOrder(any()));

        verify(validator, times(1)).validateCategorySection(any(), any());
        verify(inboundOrderRepository, times(0)).save(any());
    }

    //To-do: this test is returning a null pointer exception
    @Test
    void whenUpdating_shouldUpdateIfInboundOrderExists() throws Exception {
        SectionDTO sectionDTO = new SectionDTO(3L, 1L);
        BatchDTO batchDTO = new BatchDTO( 1L, 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8));
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(1L, LocalDate.now(), sectionDTO, List.of(batchDTO));
        InboundOrder inboundOrder = new InboundOrder();
        when(inboundConverter.dtoToEntity(any())).thenReturn(inboundOrder);
        when(inboundOrderRepository.findById(anyLong())).thenReturn(Optional.of(inboundOrder));

        assertDoesNotThrow(() -> subject.updateInboundOrder(inboundOrderDTO));

        verify(inboundOrderRepository, times(1)).save(any());
    }

    @Test
    void whenUpdating_shouldThrowIfInboundOrderDoesNotExist() throws Exception {
        SectionDTO sectionDTO = new SectionDTO(3L, 1L);
        BatchDTO batchDTO = new BatchDTO( 1L, 1L, 27.3F , 20.7F, 1, 2,  LocalDate.of(2020, 1, 8),
                LocalDateTime.of(2020, 1, 8, 1, 1, 1),
                LocalDate.of(2020, 1, 8));
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(1L, LocalDate.now(), sectionDTO, List.of(batchDTO));
        when(inboundConverter.dtoToEntity(any())).thenReturn(new InboundOrder());
        when(inboundOrderRepository.findById(any())).thenReturn(empty());

        assertThrows(NotFoundApiException.class, () -> subject.updateInboundOrder(inboundOrderDTO));

        verify(inboundOrderRepository, times(0)).save(any());
    }
}
