package ru.CSApp.restdemo.exception.contractor;

public class ContractorNotFoundException extends RuntimeException {
    public ContractorNotFoundException(String message){
        super(message);
    }
}
