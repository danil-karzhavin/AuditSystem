package com.thinkconstructive.restdemo.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ContractsWithContractors")
public class ContractWithContractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    ContractType contractType;
    Integer monetaryValue;

    LocalDate planStartDate;
    LocalDate planEndDate;
    LocalDate actualStartDate;
    LocalDate actualEndDate;

    @OneToOne(mappedBy = "contractWithContractor")
    @JsonManagedReference
    Contractor contractor;

    Integer contractId;
    @ManyToOne
    @JoinColumn(name = "contractId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    Contract contract;

    public ContractWithContractor(){}

    public ContractWithContractor(Integer id, String name, ContractType contractType, Integer monetaryValue, LocalDate planStartDate, LocalDate planEndDate, LocalDate actualStartDate, LocalDate actualEndDate, Contractor contractor, Integer contractId, Contract contract) {
        this.id = id;
        this.name = name;
        this.contractType = contractType;
        this.monetaryValue = monetaryValue;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.contractor = contractor;
        this.contractId = contractId;
        this.contract = contract;
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

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Integer getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(Integer monetaryValue) {
        this.monetaryValue = monetaryValue;
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

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}