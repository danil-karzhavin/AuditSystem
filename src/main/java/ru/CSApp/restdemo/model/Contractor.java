package ru.CSApp.restdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "Contractors")
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String address;

    @OneToOne(mappedBy = "contractor")
    @JsonManagedReference
    INN inn;

    @Column(name = "contractWithContractorId", insertable = false, updatable = false)
    Integer contractWithContractorId;
    @OneToOne(cascade = CascadeType.ALL) // Указывает на отношение "один к одному" с каскадным типом `ALL`. Это означает, что все операции каскада (например, persist, remove) также будут применены к связанному профилю.
    @JoinColumn(name = "contractWithContractorId", referencedColumnName = "id")
    @JsonBackReference
    ContractWithContractor contractWithContractor;
}
