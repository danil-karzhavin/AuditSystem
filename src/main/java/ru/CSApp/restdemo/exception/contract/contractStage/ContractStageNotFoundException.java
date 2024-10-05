package ru.CSApp.restdemo.exception.contract.contractStage;

public class ContractStageNotFoundException extends RuntimeException{
    public ContractStageNotFoundException(String msg) {
        super(msg);
    }
}
