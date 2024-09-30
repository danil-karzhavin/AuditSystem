package ru.CSApp.restdemo.service.contract.contractStage.spendingSalary;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.ContractNotFoundException;
import ru.CSApp.restdemo.model.SpendingSalary;
import ru.CSApp.restdemo.repository.contract.stage.spendingSalary.ISpendingSalaryRepository;

import java.util.List;

@Service
public class SpendingSalaryService implements ISpendingSalaryService{
    ISpendingSalaryRepository ISpendingSalaryRepository;

    public SpendingSalaryService(ISpendingSalaryRepository spendingSalaryRepository) {
        this.ISpendingSalaryRepository = spendingSalaryRepository;
    }

    @Override
    public SpendingSalary getSpendingSalaryById(Integer spendingSalaryId) {
        if (ISpendingSalaryRepository.findById(spendingSalaryId).isEmpty())
            throw new ContractNotFoundException("There is no object with such Id"); // добавить свое исключение

        SpendingSalary spendingSalary = ISpendingSalaryRepository.findById(spendingSalaryId).get();
        return spendingSalary;
    }

    @Override
    public List<SpendingSalary> getSpendingSalariesByContractStageId(Integer contractStageId) {
        var spendingSalaries = ISpendingSalaryRepository.findByContractStageId(contractStageId);
        return spendingSalaries;
    }

    @Override
    public SpendingSalary updateSpendingSalary(SpendingSalary spendingSalary) {
        ISpendingSalaryRepository.save(spendingSalary);
        return spendingSalary;
    }

    @Override
    public Integer deleteSpendingSalaryById(Integer spendingSalaryId) {
        SpendingSalary spendingSalary = getSpendingSalaryById(spendingSalaryId);

        ISpendingSalaryRepository.deleteById(spendingSalaryId);
        return 0;
    }

    @Override
    public Integer deleteAllSpendingSalariesByContractStageId(Integer contractStageId) {
        for(var obj : getSpendingSalariesByContractStageId(contractStageId)){
            ISpendingSalaryRepository.deleteById(obj.getId());
        }
        return 0;
    }
}
