package ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial;

import ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial.SpendingMaterialService;

public class SpendingMaterialDto {
    Integer id;
    String name;

    Integer monetaryValue;

    public SpendingMaterialDto(){}
    public SpendingMaterialDto(Integer id, String name, Integer monetaryValue) {
        this.id = id;
        this.name = name;
        this.monetaryValue = monetaryValue;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMonetaryValue() {
        return monetaryValue;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonetaryValue(Integer monetaryValue) {
        this.monetaryValue = monetaryValue;
    }
}
