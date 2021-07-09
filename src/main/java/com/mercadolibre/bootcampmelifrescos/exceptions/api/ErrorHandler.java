package com.mercadolibre.bootcampmelifrescos.exceptions.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

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

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ObjectError mappedObjError = getPriorityMessageMethodArgumentList(ex);

        var responseBody = new ApiException(
                Objects.requireNonNull(mappedObjError.getDefaultMessage()),
                Objects.requireNonNull(ex.getFieldError()).getField(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    private ObjectError getPriorityMessageMethodArgumentList(final MethodArgumentNotValidException ex) {
        if (ex.getAllErrors().size() > 1) {
            for (ObjectError exception : ex.getAllErrors())
                if (Objects.requireNonNull(exception.getDefaultMessage()).contains("vazio"))
                    return exception;
        }
        return ex.getAllErrors().get(0);
    }
}
