package com.mercadolibre.bootcampmelifrescos.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderStatusRequest {

    @NotNull(message = "The status code cannot be null")
    @Positive(message = "The status code must be a positive number")
    private Long statusCode;
}
