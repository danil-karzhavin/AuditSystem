package ru.CSApp.restdemo.model.contractor;

public class ContractorDto {
    Integer id;
    String name;
    String address;
    String inn;

    public ContractorDto(){}
    public ContractorDto(Integer id, String name, String address, String inn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.inn = inn;
    }

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
        this.inn = inn;
    }
}
