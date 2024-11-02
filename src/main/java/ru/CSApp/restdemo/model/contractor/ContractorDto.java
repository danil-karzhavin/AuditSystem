package ru.CSApp.restdemo.model.contractor;

public class ContractorDto {
    Integer id;
    String name;
    String address;
    String inn;

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
}
