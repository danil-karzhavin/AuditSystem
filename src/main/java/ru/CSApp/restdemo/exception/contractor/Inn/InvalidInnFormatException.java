package ru.CSApp.restdemo.exception.contractor.Inn;

public class InvalidInnFormatException extends RuntimeException{
    public InvalidInnFormatException(String message){
        super(message);
    }
}
