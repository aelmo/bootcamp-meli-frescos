package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderDTO {

    private Long orderNumber;

    @NotNull(message = "The order date can't be empty")
    private LocalDate orderDate;

    @Valid
    private SectionDTO section;

    @Valid
    private List<BatchDTO> batchStock;

    public Long getSectionCode(){
        return section.getSectionCode();
    }
}
