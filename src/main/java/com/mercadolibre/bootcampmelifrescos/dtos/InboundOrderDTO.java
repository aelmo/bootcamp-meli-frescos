package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderDTO {

    @NotBlank(message = "The order number can't be empty")
    @Positive
    private Long orderNumber;

    @NotBlank(message = "The order date can't be empty")
    private LocalDate orderDate;

    @NotBlank(message = "The section can't be empty")
    private SectionDTO section;

    @NotBlank(message = "The batch can't be empty")
    private List<BatchDTO> batchStock;

    public Long getSectionCode(){
        return section.getSectionCode();
    }
}
