package com.mercadolibre.bootcampmelifrescos.exceptions.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { BadRequestApiException.class })
    protected ResponseEntity<Object> handleBadRequestException(BadRequestApiException ex, WebRequest request) {
        String responseBody = ex.toJson();

        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { NotFoundApiException.class })
    protected ResponseEntity<Object> handleBadRequestException(NotFoundApiException ex, WebRequest request) {
        String responseBody = ex.toJson();

        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /*
    @ExceptionHandler(value = { MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleDefault(MethodArgumentNotValidException ex) {
        var responseBody = new ErrorResponseDTO();
        responseBody.setMessage(ex.getFieldError().getDefaultMessage());
        responseBody.setCause(ex.getFieldError().getField());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }*/
}
