package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderDTO {
    private int orderNumber;
    private LocalDate orderDate;
    private SectionDTO section;
    private List<BatchDTO> batchStock;
}
