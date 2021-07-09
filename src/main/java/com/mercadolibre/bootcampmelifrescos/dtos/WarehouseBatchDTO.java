package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseBatchDTO {

    @Positive(message = "The warehouse code must be a positive number")
    @NotNull(message = "The warehouse code can't be empty")
    private Long warehouseCode;

    @Positive(message = "The total quantity must be a positive number")
    @NotNull(message = "The total quantity can't be empty")
    private int totalQuantity;
}
