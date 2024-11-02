package ru.CSApp.restdemo.service.contract.contractStage.spendingSalary;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.exception.contract.contractStage.spendingMaterial.SpendingMaterialNotFoundException;
import ru.CSApp.restdemo.exception.contract.contractStage.spendingSalary.SpendingSalaryNotFoundException;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingSalary;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;
import ru.CSApp.restdemo.repository.contract.stage.spendingSalary.ISpendingSalaryRepository;

import java.util.List;

@Service
public class SpendingSalaryService implements ISpendingSalaryService{
    ISpendingSalaryRepository spendingSalaryRepository;
    IContractStageRepository contractStageRepository;

    public SpendingSalaryService(ru.CSApp.restdemo.repository.contract.stage.spendingSalary.ISpendingSalaryRepository spendingSalaryRepository, IContractStageRepository contractStageRepository) {
        this.spendingSalaryRepository = spendingSalaryRepository;
        this.contractStageRepository = contractStageRepository;
    }

    @Override
    public SpendingSalary getSpendingSalaryById(Integer spendingSalaryId) {
        if (spendingSalaryRepository.findById(spendingSalaryId).isEmpty())
            throw new SpendingSalaryNotFoundException("There is no object with such Id");
        return spendingSalaryRepository.findById(spendingSalaryId).get();
    }

    @Override
    public List<SpendingSalary> getSpendingSalariesByContractStageId(Integer contractStageId) {
        var spendingSalaries = spendingSalaryRepository.findByContractStageId(contractStageId);
        return spendingSalaries;
    }

    @Override
    public SpendingSalary createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary) {
        ContractStage contractStage = contractStageRepository.findById(contractStageId).get();
        spendingSalary.setContractStage(contractStage);
        spendingSalary.setContractStageId(contractStageId);

        contractStage.getSpendingSalaries().add(spendingSalary);
        contractStageRepository.save(contractStage);
        return spendingSalary;
    }

    @Override
    public SpendingSalary updateSpendingSalary(SpendingSalary spendingSalary) {
        spendingSalaryRepository.save(spendingSalary);
        return spendingSalary;
    }

    @Override
    public void deleteSpendingSalaryById(Integer spendingSalaryId) {
        if(spendingSalaryRepository.findById(spendingSalaryId).isEmpty())
            throw new SpendingSalaryNotFoundException("There is no object with such Id");
        spendingSalaryRepository.deleteById(spendingSalaryId);
    }

    @Override
    public void deleteAllSpendingSalariesByContractStageId(Integer contractStageId) {
        for(var obj : getSpendingSalariesByContractStageId(contractStageId)){
            spendingSalaryRepository.deleteById(obj.getId());
        }
    }
}
