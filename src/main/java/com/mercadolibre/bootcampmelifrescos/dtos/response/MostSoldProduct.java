package com.mercadolibre.bootcampmelifrescos.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostSoldProduct {
    private Long ProductId;
    private String productName;
    private Integer SoldQty;
}
