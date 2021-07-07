package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.WarehouseBatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.WarehouseBatchResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.*;
import com.mercadolibre.bootcampmelifrescos.repository.BatchRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.service.impl.WarehouseBatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class WarehouseBatchServiceTest {
    @InjectMocks
    private WarehouseBatchServiceImpl warehouseBatchService;

    @Mock
    BatchRepository batchRepository;

    @Mock
    ProductRepository productRepository;

    private Batch batchSampleProduct = new Batch();
    private Batch secondBatchSampleProduct = new Batch();
    private Product firstProduct = new Product();
    private InboundOrder inboundOrder = new InboundOrder();
    private InboundOrder secondInboundOrder = new InboundOrder();
    private Section section = new Section();
    private Section secondSection = new Section();
    private Agent agent = new Agent();
    private Warehouse warehouse = new Warehouse();
    private Warehouse secondWarehouse = new Warehouse();
    List<WarehouseBatchDTO> warehouseBatchDTOList = new ArrayList<>();
    WarehouseBatchResponse warehouseBatchResponse = new WarehouseBatchResponse();
    List<Batch> batchList = new ArrayList<>();
    Set<Batch> batchSet= new HashSet<>();
    Set<Section> sectionsSet= new HashSet<>();

    @BeforeEach
    public void init() {
        warehouse = new Warehouse(1L, "Floripa", sectionsSet);
        secondWarehouse = new Warehouse(2L, "SP", sectionsSet);
        section = new Section(1L, warehouse, new Category());
        secondSection = new Section(2L, secondWarehouse, new Category());
        inboundOrder = new InboundOrder(1L, LocalDate.now(), section, batchSet, agent);
        secondInboundOrder = new InboundOrder(2L, LocalDate.now(), secondSection, batchSet, agent);
        firstProduct = new Product(3L, "sample", new Seller(), new Category());
        batchSampleProduct = new Batch(1L, 10F, 10F, 10, 10, LocalDate.now(), LocalDateTime.of(2021, 01, 10, 01, 00), LocalDate.now(), firstProduct, inboundOrder);
        secondBatchSampleProduct = new Batch(2L, 10F, 10F, 10, 7, LocalDate.now(), LocalDateTime.of(2021, 01, 10, 01, 00), LocalDate.now(), firstProduct, secondInboundOrder);
        batchList.add(batchSampleProduct);
        batchList.add(secondBatchSampleProduct);
        WarehouseBatchDTO firstWarehouseBatchDTO = new WarehouseBatchDTO(1L, 10);
        WarehouseBatchDTO secondWarehouseBatchDTO = new WarehouseBatchDTO(2L, 7);
        warehouseBatchDTOList.add(firstWarehouseBatchDTO);
        warehouseBatchDTOList.add(secondWarehouseBatchDTO);
        warehouseBatchResponse = new WarehouseBatchResponse(1L, warehouseBatchDTOList);

    }

    @Test
    public void shouldReturnWarehouseBatchTotalQuantityByProduct() throws Exception {
        WarehouseBatchResponse expectedResult = warehouseBatchResponse;
        when(productRepository.findById(notNull())).thenReturn(java.util.Optional.ofNullable(firstProduct));
        when(batchRepository.findAllByProduct(notNull())).thenReturn(batchList);
        WarehouseBatchResponse result = warehouseBatchService.getWarehouseBatchQuantityByProduct(1L);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldThrownExceptionWhenProductIsNotFound(){
        when(productRepository.findById(any())).thenReturn(empty());
        assertThrows(NotFoundApiException.class,() -> warehouseBatchService.getWarehouseBatchQuantityByProduct(new Product().getId()));
    }
}
