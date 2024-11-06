package ru.CSApp.restdemo.model.contract.contractWithContractor;

import ru.CSApp.restdemo.model.contract.ContractType;

import java.time.LocalDate;

public class ContractWithContractorDto {
    Integer id;
    String name;
    ContractType type;
    Integer monetaryValue;

    LocalDate planStartDate;
    LocalDate planEndDate;
    LocalDate actualStartDate;
    LocalDate actualEndDate;
    Integer contractId;

    public ContractWithContractorDto(){}
    public ContractWithContractorDto(Integer id, String name, ContractType type, Integer monetaryValue, LocalDate planStartDate, LocalDate planEndDate, LocalDate actualStartDate, LocalDate actualEndDate, Integer contractId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.monetaryValue = monetaryValue;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.contractId = contractId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ContractType getType() {
        return type;
    }

    public Integer getMonetaryValue() {
        return monetaryValue;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public void setMonetaryValue(Integer monetaryValue) {
        this.monetaryValue = monetaryValue;
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

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
}
