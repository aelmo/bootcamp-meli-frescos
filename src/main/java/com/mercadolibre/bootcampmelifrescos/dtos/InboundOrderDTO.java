package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderDTO {
    private Long orderNumber;
    private LocalDate orderDate;
    private SectionDTO section;
    private List<BatchDTO> batchStock;

    public Long getSectionCode(){
        return section.getSectionCode();
    }
}
