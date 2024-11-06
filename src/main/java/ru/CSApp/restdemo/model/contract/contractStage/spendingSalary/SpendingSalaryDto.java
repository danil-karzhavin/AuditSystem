package ru.CSApp.restdemo.model.contract.contractStage.spendingSalary;

import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterialDto;
import ru.CSApp.restdemo.service.contract.contractStage.spendingSalary.SpendingSalaryService;

public class SpendingSalaryDto {
    Integer id;
    String name;
    String surname;

    Integer monetaryValue;

    public SpendingSalaryDto(){}
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMonetaryValue(Integer monetaryValue) {
        this.monetaryValue = monetaryValue;
    }
}
