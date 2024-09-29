package ru.CSApp.restdemo.service.contract.contractStage.spendingSalary;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.ContractNotFoundException;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.model.SpendingSalary;
import ru.CSApp.restdemo.repository.contract.stage.spendingSalary.SpendingSalaryRepository;

import java.util.List;

@Service
public class SpendingSalaryService implements ISpendingSalaryService{
    SpendingSalaryRepository spendingSalaryRepository;

    public SpendingSalaryService(SpendingSalaryRepository spendingSalaryRepository) {
        this.spendingSalaryRepository = spendingSalaryRepository;
    }

    @Override
    public SpendingSalary getSpendingSalaryById(Integer spendingSalaryId) {
        if (spendingSalaryRepository.findById(spendingSalaryId).isEmpty())
            throw new ContractNotFoundException("There is no object with such Id"); // добавить свое исключение

        SpendingSalary spendingSalary = spendingSalaryRepository.findById(spendingSalaryId).get();
        return spendingSalary;
    }

    @Override
    public List<SpendingSalary> getSpendingSalariesByContractStageId(Integer contractStageId) {
        var spendingSalaries = spendingSalaryRepository.findByContractStageId(contractStageId);
        return spendingSalaries;
    }
}
