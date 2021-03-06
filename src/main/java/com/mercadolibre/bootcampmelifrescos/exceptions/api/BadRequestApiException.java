package com.mercadolibre.bootcampmelifrescos.exceptions.api;

import org.springframework.http.HttpStatus;

public class BadRequestApiException extends ApiException {

    private final static String CODE = "BAD_REQUEST";
    private final static Integer STATUS_CODE = HttpStatus.BAD_REQUEST.value();

    public BadRequestApiException(String description) {
        super(CODE, description, STATUS_CODE);
    }
}