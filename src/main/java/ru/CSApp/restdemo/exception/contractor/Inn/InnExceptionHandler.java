package ru.CSApp.restdemo.exception.contractor.Inn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "ru.CSApp.restdemo.controller.contractor")
public class InnExceptionHandler {
    @ExceptionHandler(InvalidInnFormatException.class)
    public ResponseEntity<Object> handlerInvalidInnFormatException(InvalidInnFormatException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
