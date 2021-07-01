package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderRequest {
    private InboundOrderDTO inboundOrder;
}

@NoArgsConstructor
@AllArgsConstructor
class InboundOrderDTO {
    private int orderNumber;
    private LocalDate orderDate;
    private SectionDTO section;
    private List<BatchDTO> batchStock;
}

@NoArgsConstructor
@AllArgsConstructor
class SectionDTO {
    private Long sectionCode;
    private Long warehouseCode;
}
