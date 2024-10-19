package ru.CSApp.restdemo.exception.contractor.Inn;

public class InvalidInnFormat extends RuntimeException{
    public InvalidInnFormat(String message){
        super(message);
    }
}
