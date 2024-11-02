package ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.contractStage.spendingMaterial.SpendingMaterialNotFoundException;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;
import ru.CSApp.restdemo.repository.contract.stage.spendingMaterial.ISpendingMaterialRepository;

import java.util.List;
@Service
public class SpendingMaterialService implements ISpendingMaterialService {
    ISpendingMaterialRepository spendingMaterialRepository;
    IContractStageRepository contractStageRepository;

    public SpendingMaterialService(ISpendingMaterialRepository spendingMaterialRepository, IContractStageRepository contractStageRepository) {
        this.spendingMaterialRepository = spendingMaterialRepository;
        this.contractStageRepository = contractStageRepository;
    }

    @Override
    public SpendingMaterial getSpendingMaterialById(Integer spendingMaterialId) {
        if (spendingMaterialRepository.findById(spendingMaterialId).isEmpty())
            throw new SpendingMaterialNotFoundException("Not found spending material with such Id");
        return spendingMaterialRepository.findById(spendingMaterialId).get();
    }

    @Override
    public List<SpendingMaterial> getSpendingMaterialsByContractStageId(Integer contractStageId) {
        var spendingMaterials = spendingMaterialRepository.findByContractStageId(contractStageId);
        return spendingMaterials;
    }

    @Override
    public SpendingMaterial createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial) {
        ContractStage contractStage = contractStageRepository.findById(contractStageId).get();
        spendingMaterial.setContractStage(contractStage);
        spendingMaterial.setContractStageId(contractStageId);

        contractStage.getSpendingMaterials().add(spendingMaterial);
        contractStageRepository.save(contractStage);
        return spendingMaterial;
    }

    @Override
    public SpendingMaterial updateSpendingMaterial(SpendingMaterial spendingMaterial) {
        spendingMaterialRepository.save(spendingMaterial);
        return spendingMaterial;
    }

    @Override
    public void deleteSpendingMaterialById(Integer spendingMaterialId) {
        if(spendingMaterialRepository.findById(spendingMaterialId).isEmpty())
            throw new SpendingMaterialNotFoundException("Not found spending material with such Id");
        spendingMaterialRepository.deleteById(spendingMaterialId);
    }

    @Override
    public void deleteAllSpendingMaterialByContractStageId(Integer contractStageId) {
        for(var obj : getSpendingMaterialsByContractStageId(contractStageId)){
            spendingMaterialRepository.deleteById(obj.getId());
        }
    }
}
