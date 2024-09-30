package ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.ContractNotFoundException;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.repository.contract.stage.spendingMaterial.ISpendingMaterialRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class SpendingMaterialService implements ISpendingMaterialService {
    ISpendingMaterialRepository ISpendingMaterialRepository;

    public SpendingMaterialService(ISpendingMaterialRepository spendingMaterialRepository) {
        this.ISpendingMaterialRepository = spendingMaterialRepository;
    }

    @Override
    public SpendingMaterial getSpendingMaterialById(Integer spendingMaterialId) {
        if (ISpendingMaterialRepository.findById(spendingMaterialId).isEmpty())
            throw new ContractNotFoundException("There is no object with such Id"); // добавить свое исключение

        SpendingMaterial spendingMaterial = ISpendingMaterialRepository.findById(spendingMaterialId).get();
        return spendingMaterial;
    }

    @Override
    public List<SpendingMaterial> getSpendingMaterialsByContractStageId(Integer contractStageId) {
        var spendingMaterials = ISpendingMaterialRepository.findByContractStageId(contractStageId);
        return spendingMaterials;
    }

    @Override
    public SpendingMaterial updateSpendingMaterial(SpendingMaterial spendingMaterial) {
        ISpendingMaterialRepository.save(spendingMaterial);
        return spendingMaterial;
    }

    @Override
    public Integer deleteSpendingMaterialById(Integer spendingMaterialId) {
        SpendingMaterial spendingMaterial = getSpendingMaterialById(spendingMaterialId);

        ISpendingMaterialRepository.deleteById(spendingMaterialId);
        return spendingMaterialId;
    }

    @Override
    public Integer deleteAllSpendingMaterialByContractStageId(Integer contractStageId) {
        for(var obj : getSpendingMaterialsByContractStageId(contractStageId)){
            ISpendingMaterialRepository.deleteById(obj.getId());
        }
        return 0;
    }
}
