package com.mercadolibre.bootcampmelifrescos.dtos.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String cause;
}
