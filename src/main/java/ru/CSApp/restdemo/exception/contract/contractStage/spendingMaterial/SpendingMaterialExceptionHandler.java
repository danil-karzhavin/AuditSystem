package ru.CSApp.restdemo.exception.contract.contractStage.spendingMaterial;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "ru.CSApp.restdemo.controller.contract.contractStage.spendingMaterial")
public class SpendingMaterialExceptionHandler {
    @ExceptionHandler(SpendingMaterialNotFoundException.class)
    public ResponseEntity<Object> handlerSpendingMaterialNotFoundException(SpendingMaterialNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
