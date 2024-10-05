package ru.CSApp.restdemo.exception.contract.contractStage.spendingSalary;

public class SpendingSalaryNotFoundException extends RuntimeException{
    public SpendingSalaryNotFoundException(String msg) {
        super(msg);
    }
}
