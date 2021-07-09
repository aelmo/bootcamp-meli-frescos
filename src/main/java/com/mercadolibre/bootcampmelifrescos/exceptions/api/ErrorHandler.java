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
public class ErrorHandler {

    @ExceptionHandler(value = { BadRequestApiException.class })
    protected ResponseEntity<Object> handleBadRequestException(BadRequestApiException ex) {

        var responseBody = new ApiException(HttpStatus.BAD_REQUEST.toString(),ex.getDescription(),HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(responseBody.toJson(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { NotFoundApiException.class })
    protected ResponseEntity<Object> handleBadRequestException(NotFoundApiException ex) {
        var responseBody = new ApiException(HttpStatus.NOT_FOUND.toString(),ex.getDescription(),HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(responseBody.toJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ObjectError mappedObjError = getPriorityMessageMethodArgumentList(ex);
        var responseBody = new ApiException(
                Objects.requireNonNull(mappedObjError.getDefaultMessage()),
                Objects.requireNonNull(ex.getFieldError()).getField(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(responseBody.toJson(), HttpStatus.BAD_REQUEST);
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
