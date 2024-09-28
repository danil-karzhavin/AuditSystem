package ru.CSApp.restdemo.exception;

public class ContractNotFoundException extends RuntimeException {
    public ContractNotFoundException(String message){
        super(message);
    }
}