package ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial;

import ru.CSApp.restdemo.model.SpendingMaterial;

import java.util.List;

public interface ISpendingMaterialService {
    public SpendingMaterial getSpendingMaterialById(Integer spendingMaterialId);
    public List<SpendingMaterial> getSpendingMaterialsByContractStageId(Integer contractStageId);
}
