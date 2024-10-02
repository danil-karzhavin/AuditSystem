package ru.CSApp.restdemo.service.contract.contractStage;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.ContractNotFoundException;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.model.SpendingSalary;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;

import java.util.List;
@Service
public class ContractStageService implements IContractStageService{
    IContractStageRepository contractStageRepository;

    public ContractStageService(IContractStageRepository IContractStageRepository) {
        this.contractStageRepository = IContractStageRepository;
    }

    @Override
    public ContractStage getContractStageById(Integer contractStageId){
        if(contractStageRepository.findById(contractStageId).isEmpty())
            throw new ContractNotFoundException("There is no object with such Id"); // добавить свое исключение

        ContractStage contractStage = contractStageRepository.findById(contractStageId).get();
        return contractStage;
    }

    @Override
    public List<ContractStage> getContractStagesByContractId(Integer contractId) {
        var contractStages = contractStageRepository.findByContractId(contractId);
        return contractStages;
    }

    @Override
    public void createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial) {
        ContractStage contractStage = getContractStageById(contractStageId);
        spendingMaterial.setContractStage(contractStage);
        spendingMaterial.setContractStageId(contractStageId);

        contractStage.getSpendingMaterials().add(spendingMaterial);
        contractStageRepository.save(contractStage);
    }

    @Override
    public void createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary) {
        ContractStage contractStage = getContractStageById(contractStageId);
        spendingSalary.setContractStage(contractStage);
        spendingSalary.setContractStageId(contractStageId);

        contractStage.getSpendingSalaries().add(spendingSalary);
        contractStageRepository.save(contractStage);
    }

    @Override
    public ContractStage updateContactStage(ContractStage contractStage) {
        contractStageRepository.save(contractStage);
        return contractStage;
    }

    @Override
    public Integer deleteContractStageById(Integer contractStageId) {
        contractStageRepository.deleteById(contractStageId);
        return contractStageId;
    }

    @Override
    public Integer deleteAllContractStagesByContractId(Integer contractId) {
        for(var obj : getContractStagesByContractId(contractId)){
            contractStageRepository.deleteById(obj.getId());
        }
        return 0;
    }
}
