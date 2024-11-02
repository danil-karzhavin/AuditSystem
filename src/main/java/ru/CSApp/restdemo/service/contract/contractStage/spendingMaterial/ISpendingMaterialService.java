package ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial;

import ru.CSApp.restdemo.model.SpendingMaterial;

import java.util.List;

public interface ISpendingMaterialService {
    public SpendingMaterial getSpendingMaterialById(Integer spendingMaterialId);
    public List<SpendingMaterial> getSpendingMaterialsByContractStageId(Integer contractStageId);

    public SpendingMaterial createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial);
    public SpendingMaterial updateSpendingMaterial(SpendingMaterial spendingMaterial);

    public void deleteSpendingMaterialById(Integer spendingMaterialId);
    public void deleteAllSpendingMaterialByContractStageId(Integer contractStageId);
}
