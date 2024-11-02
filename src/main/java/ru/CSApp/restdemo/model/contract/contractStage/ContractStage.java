package ru.CSApp.restdemo.model.contract.contractStage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterial;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalary;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ContractStages")
public class ContractStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    LocalDate planStartDate;
    LocalDate planEndDate;
    LocalDate actualStartDate;
    LocalDate actualEndDate;

    Integer monetaryValue;

    Integer contractId;
    @ManyToOne
    @JoinColumn(name = "contractId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    Contract contract;

    @OneToMany(mappedBy = "contractStage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<SpendingMaterial> spendingMaterials;

    @OneToMany(mappedBy = "contractStage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<SpendingSalary> spendingSalaries;

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

    public List<SpendingMaterial> getSpendingMaterials() {
        if (spendingMaterials == null)
            spendingMaterials = new ArrayList<SpendingMaterial>();
        return spendingMaterials;
    }

    public void setSpendingMaterials(List<SpendingMaterial> spendingMaterials) {
        this.spendingMaterials = spendingMaterials;
    }

    public List<SpendingSalary> getSpendingSalaries() {
        if(spendingSalaries == null)
            spendingSalaries = new ArrayList<SpendingSalary>();
        return spendingSalaries;
    }

    public void setSpendingSalaries(List<SpendingSalary> spendingSalaries) {
        this.spendingSalaries = spendingSalaries;
    }
}
