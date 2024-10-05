package ru.CSApp.restdemo.exception.contract.contractStage.spendingMaterial;

public class SpendingMaterialNotFoundException extends RuntimeException{
    public SpendingMaterialNotFoundException(String msg) {
        super(msg);
    }
}
