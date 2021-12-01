package com.bank.account.execption;

import com.bank.account.entity.ErrorRessource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FunctionnalExceptionHandler {
    @ExceptionHandler(value = FunctionnalException.class)
    public ResponseEntity<Object> exception(FunctionnalException exception) {
        return new ResponseEntity<>(ErrorRessource.builder().code(exception.getCode()).message(exception.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
