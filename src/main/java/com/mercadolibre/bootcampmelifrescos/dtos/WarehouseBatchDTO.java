package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseBatchDTO {

    @Positive
    @NotBlank
    private Long warehouseCode;

    @Positive
    @NotBlank
    private int totalQuantity;
}
