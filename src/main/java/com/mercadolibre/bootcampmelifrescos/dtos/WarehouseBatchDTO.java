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
    @NotBlank(message = "The warehouse code can't be empty")
    private Long warehouseCode;

    @Positive
    @NotBlank(message = "The total quantity can't be empty")
    private int totalQuantity;
}
