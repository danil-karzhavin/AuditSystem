package ru.CSApp.restdemo.model.contractor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ru.CSApp.restdemo.exception.contractor.Inn.InvalidInnFormatException;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;

import javax.persistence.*;

@Entity
@Table(name = "Contractors")
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String address;

    String inn;

    @Column(name = "contractWithContractorId", insertable = false, updatable = false)
    Integer contractWithContractorId;
    @OneToOne(cascade = CascadeType.ALL) // Указывает на отношение "один к одному" с каскадным типом `ALL`. Это означает, что все операции каскада (например, persist, remove) также будут применены к связанному профилю.
    @JoinColumn(name = "contractWithContractorId", referencedColumnName = "id")
    @JsonBackReference
    ContractWithContractor contractWithContractor;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getInn() {
        return inn;
    }

    public Integer getContractWithContractorId() {
        return contractWithContractorId;
    }

    public ContractWithContractor getContractWithContractor() {
        return contractWithContractor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInn(String inn) {
        if(inn.length() == 12 && inn.chars().allMatch(Character::isDigit))
            this.inn = inn;
        else throw new InvalidInnFormatException("Неверный формат ИНН");
    }

    public void setContractWithContractorId(Integer contractWithContractorId) {
        this.contractWithContractorId = contractWithContractorId;
    }

    public void setContractWithContractor(ContractWithContractor contractWithContractor) {
        this.contractWithContractor = contractWithContractor;
    }
}
