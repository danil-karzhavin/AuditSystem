package ru.CSApp.restdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "SpendingMaterials")
public class SpendingMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    Integer monetaryValue;

    Integer contractStageId;
    @ManyToOne
    @JoinColumn(name = "contractStageId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    ContractStage contractStage;

    public SpendingMaterial(){};
    public SpendingMaterial(Integer id, String name, Integer monetaryValue, Integer contractStageId, ContractStage contractStage) {
        this.id = id;
        this.name = name;
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
