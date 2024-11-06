package ru.CSApp.restdemo.model.contract.contractStage;

import java.time.LocalDate;

public class ContractStageDto {
    Integer id;
    String name;

    LocalDate planStartDate;
    LocalDate planEndDate;
    LocalDate actualStartDate;
    LocalDate actualEndDate;

    Integer monetaryValue;

    public ContractStageDto(){}
    public ContractStageDto(Integer id, String name, LocalDate planStartDate, LocalDate planEndDate, LocalDate actualStartDate, LocalDate actualEndDate, Integer monetaryValue) {
        this.id = id;
        this.name = name;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.monetaryValue = monetaryValue;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public LocalDate getPlanEndDate() {
        return planEndDate;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
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

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public void setPlanEndDate(LocalDate planEndDate) {
        this.planEndDate = planEndDate;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public void setMonetaryValue(Integer monetaryValue) {
        this.monetaryValue = monetaryValue;
    }
}
