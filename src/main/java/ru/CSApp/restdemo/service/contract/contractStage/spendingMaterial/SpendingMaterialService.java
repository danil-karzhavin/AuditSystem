package ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.contractStage.spendingMaterial.SpendingMaterialNotFoundException;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterial;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterialDto;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;
import ru.CSApp.restdemo.repository.contract.stage.spendingMaterial.ISpendingMaterialRepository;
import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;

import java.util.List;
import java.util.Optional;

@Service
public class SpendingMaterialService implements ISpendingMaterialService {
    ISpendingMaterialRepository spendingMaterialRepository;
    IContractStageService contractStageService;

    public SpendingMaterialService(ISpendingMaterialRepository spendingMaterialRepository, IContractStageService contractStageService) {
        this.spendingMaterialRepository = spendingMaterialRepository;
        this.contractStageService = contractStageService;
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
    public SpendingMaterial createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterialDto spendingMaterialDto) {
        SpendingMaterial spendingMaterial = new SpendingMaterial();

        Optional.ofNullable(spendingMaterialDto.getName()).ifPresent(spendingMaterial::setName);
        Optional.ofNullable(spendingMaterialDto.getMonetaryValue()).ifPresent(spendingMaterial::setMonetaryValue);

        ContractStage contractStage = contractStageService.getContractStageById(contractStageId);
        spendingMaterial.setContractStage(contractStage);
        spendingMaterial.setContractStageId(contractStageId);

        contractStage.getSpendingMaterials().add(spendingMaterial);
        spendingMaterialRepository.save(spendingMaterial);
        contractStageService.save(contractStage);
        return spendingMaterial;
    }

    @Override
    public SpendingMaterial updateSpendingMaterial(SpendingMaterialDto spendingMaterialDto) {
        SpendingMaterial spendingMaterial = getSpendingMaterialById(spendingMaterialDto.getId());

        Optional.ofNullable(spendingMaterialDto.getName()).ifPresent(spendingMaterial::setName);
        Optional.ofNullable(spendingMaterialDto.getMonetaryValue()).ifPresent(spendingMaterial::setMonetaryValue);

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
