package ru.CSApp.restdemo.model.contract;

import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;

import java.time.LocalDate;
import java.util.List;

public class ContractDto {
    public ContractDto(){}
    public ContractDto(Integer id, String name, ContractType type, LocalDate planStartDate, LocalDate planEndDate, LocalDate actualStartDate, LocalDate actualEndDate, Integer monetaryValue, List<ContractStage> stages, List<ContractWithContractor> subContracts) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.monetaryValue = monetaryValue;
        this.stages = stages;
        this.subContracts = subContracts;
    }

    Integer id;
    String name;
    ContractType type; // тип контракта

    LocalDate planStartDate;
    LocalDate planEndDate;
    LocalDate actualStartDate;
    LocalDate actualEndDate;

    Integer monetaryValue;
    List<ContractStage> stages;
    List<ContractWithContractor> subContracts;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ContractType getType() {
        return type;
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

    public void setType(ContractType type) {
        this.type = type;
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

    public void setStages(List<ContractStage> stages) {
        this.stages = stages;
    }

    public void setSubContracts(List<ContractWithContractor> subContracts) {
        this.subContracts = subContracts;
    }
}
