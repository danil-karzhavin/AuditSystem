package ru.CSApp.restdemo.model.contract;

import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;

import java.time.LocalDate;
import java.util.List;

public class ContractDto {
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
}
