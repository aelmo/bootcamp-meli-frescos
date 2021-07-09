package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {

    @Positive
    @NotBlank(message = "The section can't be empty")
    private Long sectionCode;

    @Positive
    @NotBlank(message = "The warehouse code can't be empty")
    private Long warehouseCode;
}
