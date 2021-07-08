package com.mercadolibre.bootcampmelifrescos.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestProductsDTO {
    @NotNull(message = "The product id cannot be null")
    private Long productId;

    @NotNull(message = "The product quantity cannot be null")
    private Integer quantity;
}
