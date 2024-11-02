package ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial;

public class SpendingMaterialDto {
    Integer id;
    String name;

    Integer monetaryValue;

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
}
