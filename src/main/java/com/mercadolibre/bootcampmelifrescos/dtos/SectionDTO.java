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
    @NotBlank
    private Long sectionCode;

    @Positive
    @NotBlank
    private Long warehouseCode;
}
