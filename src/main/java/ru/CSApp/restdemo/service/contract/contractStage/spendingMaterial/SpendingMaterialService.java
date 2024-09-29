package ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.ContractNotFoundException;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.repository.contract.stage.ContractStageRepository;
import ru.CSApp.restdemo.repository.contract.stage.spendingMaterial.SpendingMaterialRepository;

import java.util.List;
@Service
public class SpendingMaterialService implements ISpendingMaterialService {
    SpendingMaterialRepository spendingMaterialRepository;

    public SpendingMaterialService(SpendingMaterialRepository spendingMaterialRepository) {
        this.spendingMaterialRepository = spendingMaterialRepository;
    }

    @Override
    public SpendingMaterial getSpendingMaterialById(Integer spendingMaterialId) {
        if (spendingMaterialRepository.findById(spendingMaterialId).isEmpty())
            throw new ContractNotFoundException("There is no object with such Id"); // добавить свое исключение

        SpendingMaterial spendingMaterial = spendingMaterialRepository.findById(spendingMaterialId).get();
        return spendingMaterial;
    }

    @Override
    public List<SpendingMaterial> getSpendingMaterialsByContractStageId(Integer contractStageId) {
        var spendingMaterials = spendingMaterialRepository.findByContractStageId(contractStageId);
        return spendingMaterials;
    }
}
