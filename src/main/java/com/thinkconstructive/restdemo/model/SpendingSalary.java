package com.thinkconstructive.restdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "SpendingSalaries")
public class SpendingSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String surname;

    Integer monetaryValue;

    Integer contractStageId;
    @ManyToOne
    @JoinColumn(name = "contractStageId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    ContractStage contractStage;

    public SpendingSalary(){};
    public SpendingSalary(Integer id, String name, String surname, Integer monetaryValue, Integer contractStageId, ContractStage contractStage) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.monetaryValue = monetaryValue;
        this.contractStageId = contractStageId;
        this.contractStage = contractStage;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(Integer monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    public Integer getContractStageId() {
        return contractStageId;
    }

    public void setContractStageId(Integer contractStageId) {
        this.contractStageId = contractStageId;
    }

    public ContractStage getContractStage() {
        return contractStage;
    }

    public void setContractStage(ContractStage contractStage) {
        this.contractStage = contractStage;
    }
}
