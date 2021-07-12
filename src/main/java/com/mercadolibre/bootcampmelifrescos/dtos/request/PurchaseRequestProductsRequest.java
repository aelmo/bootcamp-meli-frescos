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
public class PurchaseRequestProductsRequest {

    @NotNull(message = "The product id cannot be null")
    @Positive(message = "The product id must be a positive number")
    private Long productId;

    @NotNull(message = "The product quantity cannot be null")
    @Positive(message = "The quantity must be a positive number")
    private Integer quantity;
}
