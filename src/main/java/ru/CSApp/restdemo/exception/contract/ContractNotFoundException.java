package ru.CSApp.restdemo.exception.contract;

public class ContractNotFoundException extends RuntimeException {
    public ContractNotFoundException(String message){
        super(message);
    }
}