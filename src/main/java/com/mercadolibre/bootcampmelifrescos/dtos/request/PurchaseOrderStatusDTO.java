package com.mercadolibre.bootcampmelifrescos.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderStatusDTO {

    @NotNull(message = "The status code cannot be null")
    private Long statusCode;
}
