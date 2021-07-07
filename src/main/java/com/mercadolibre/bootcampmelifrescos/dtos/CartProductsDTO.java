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
public class CartProductsDTO {
    @NotBlank(message = "the product id cannot be blank")
    private Long productId;

    @Positive(message = "The quantity cannot be negative")
    @NotBlank(message = "The quantity cannot be null")
    private Integer quantity;
}
