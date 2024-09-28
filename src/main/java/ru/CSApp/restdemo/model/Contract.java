package ru.CSApp.restdemo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    ContractType type; // тип контракта

    LocalDate planStartDate;
    LocalDate planEndDate;
    LocalDate actualStartDate;
    LocalDate actualEndDate;

    Integer monetaryValue;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<ContractStage> stages; // этапы договоров

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<ContractWithContractor> subContracts;// контрагенты

    public Contract(){}
    public Contract(Integer id, String name, ContractType type, LocalDate planStartDate, LocalDate planEndDate, LocalDate actualStartDate, LocalDate actualEndDate, Integer monetaryValue, List<ContractStage> stages, List<ContractWithContractor> contracts) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.monetaryValue = monetaryValue;
        this.stages = stages;
        this.subContracts = contracts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(String type) {
        this.type = ContractType.valueOf(type.toLowerCase());
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public LocalDate getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(LocalDate planEndDate) {
        this.planEndDate = planEndDate;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public Integer getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(Integer monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    public List<ContractStage> getStages() {
        if (stages == null)
            stages = new ArrayList<ContractStage>();
        return stages;
    }

    public void setStages(List<ContractStage> stages) {
        this.stages = stages;
    }

    public List<ContractWithContractor> getSubContracts() {
        if (subContracts == null)
            subContracts = new ArrayList<ContractWithContractor>();
        return subContracts;
    }

    public void setSubContracts(List<ContractWithContractor> subContracts) {
        this.subContracts = subContracts;
    }
}
