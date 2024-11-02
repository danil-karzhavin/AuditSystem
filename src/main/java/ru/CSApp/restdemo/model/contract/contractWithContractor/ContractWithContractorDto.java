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
}
