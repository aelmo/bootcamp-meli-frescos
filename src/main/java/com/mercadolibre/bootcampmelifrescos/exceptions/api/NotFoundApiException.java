package com.mercadolibre.bootcampmelifrescos.exceptions.api;

import org.springframework.http.HttpStatus;

public class NotFoundApiException extends ApiException {

    private final static String CODE = "NOT_FOUND";
    private final static Integer STATUS_CODE = HttpStatus.NOT_FOUND.value();

    public NotFoundApiException() {
        super(CODE, CODE, STATUS_CODE);
    }

    public NotFoundApiException(String description) {
        super(CODE, description, STATUS_CODE);
    }
}