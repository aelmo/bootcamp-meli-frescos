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
public class PurchaseRequestProductsDTO {

    @NotBlank(message = "The product id cannot be null")
    @Positive
    private Long productId;

    @NotBlank(message = "The product quantity cannot be null")
    @Positive
    private Integer quantity;
}
