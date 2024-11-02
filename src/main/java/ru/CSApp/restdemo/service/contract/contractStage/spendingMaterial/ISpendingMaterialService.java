package ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial;

import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterial;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterialDto;

import java.util.List;

public interface ISpendingMaterialService {
    public SpendingMaterial getSpendingMaterialById(Integer spendingMaterialId);
    public List<SpendingMaterial> getSpendingMaterialsByContractStageId(Integer contractStageId);

    public SpendingMaterial createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterialDto spendingMaterialDto);
    public SpendingMaterial updateSpendingMaterial(SpendingMaterialDto spendingMaterialDto);

    public void deleteSpendingMaterialById(Integer spendingMaterialId);
    public void deleteAllSpendingMaterialByContractStageId(Integer contractStageId);
}
