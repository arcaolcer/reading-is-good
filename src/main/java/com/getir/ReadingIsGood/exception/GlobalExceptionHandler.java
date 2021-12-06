package com.getir.ReadingIsGood.exception;

import com.getir.ReadingIsGood.model.contracts.baseapi.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach( (fieldError -> {
            errors.add(fieldError.getField() + " :: " + fieldError.getDefaultMessage() + " :: rejected value : " + fieldError.getRejectedValue());
        }) );
        ErrorResponse errorResponse = new ErrorResponse(errors);
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReadingIsGoodException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ReadingIsGoodException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }
}
