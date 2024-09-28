package ru.CSApp.restdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "INNs")
public class INN {
    @Id
    String inn;

    @Column(name = "contractorId", insertable = false, updatable = false)
    Integer contractorId;
    @OneToOne(cascade = CascadeType.ALL) // Указывает на отношение "один к одному" с каскадным типом `ALL`. Это означает, что все операции каскада (например, persist, remove) также будут применены к связанному профилю.
    @JoinColumn(name = "contractorId", referencedColumnName = "id")
    @JsonBackReference
    Contractor contractor;

    public INN(){}

    public INN(String inn, Integer contractorId, Contractor contractor) {
        this.inn = inn;
        this.contractorId = contractorId;
        this.contractor = contractor;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Integer getContractorId() {
        return contractorId;
    }

    public void setContractorId(Integer contractorId) {
        this.contractorId = contractorId;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    boolean isCorrect(){
        return true;
    }
}
