package ru.CSApp.restdemo.model.contract.contractStage.spendingSalary;

public class SpendingSalaryDto {
    Integer id;
    String name;
    String surname;

    Integer monetaryValue;

    public SpendingSalaryDto(Integer id, String name, String surname, Integer monetaryValue) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.monetaryValue = monetaryValue;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getMonetaryValue() {
        return monetaryValue;
    }
}
