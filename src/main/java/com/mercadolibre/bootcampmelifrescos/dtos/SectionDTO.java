package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {

    @NotNull(message = "The section can't be empty")
    private Long sectionCode;

    @Positive(message = "The warehouse code must be a positive number")
    @NotNull(message = "The warehouse code can't be empty")
    private Long warehouseCode;
}
