package ru.CSApp.restdemo.service.contract.contractStage.spendingSalary;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
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
        try{
            if (spendingSalaryRepository.findById(spendingSalaryId).isEmpty())
              throw new SpendingSalaryNotFoundException("There is no object with such Id");
            return spendingSalaryRepository.findById(spendingSalaryId).get();
        }
        catch (SpendingSalaryNotFoundException ex){
            return null;
        }
    }

    @Override
    public List<SpendingSalary> getSpendingSalariesByContractStageId(Integer contractStageId) {
        var spendingSalaries = spendingSalaryRepository.findByContractStageId(contractStageId);
        return spendingSalaries;
    }

    @Override
    public void createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary) {
        ContractStage contractStage = contractStageRepository.findById(contractStageId).get();
        spendingSalary.setContractStage(contractStage);
        spendingSalary.setContractStageId(contractStageId);

        contractStage.getSpendingSalaries().add(spendingSalary);
        contractStageRepository.save(contractStage);
    }

    @Override
    public SpendingSalary updateSpendingSalary(SpendingSalary spendingSalary) {
        spendingSalaryRepository.save(spendingSalary);
        return spendingSalary;
    }

    @Override
    public Integer deleteSpendingSalaryById(Integer spendingSalaryId) {
        SpendingSalary spendingSalary = getSpendingSalaryById(spendingSalaryId);

        spendingSalaryRepository.deleteById(spendingSalaryId);
        return 0;
    }

    @Override
    public Integer deleteAllSpendingSalariesByContractStageId(Integer contractStageId) {
        for(var obj : getSpendingSalariesByContractStageId(contractStageId)){
            spendingSalaryRepository.deleteById(obj.getId());
        }
        return 0;
    }
}
