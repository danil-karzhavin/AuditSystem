package ru.CSApp.restdemo.service.contract.contractStage.spendingSalary;

import ru.CSApp.restdemo.model.SpendingSalary;

import java.util.List;

public interface ISpendingSalaryService {
    public SpendingSalary getSpendingSalaryById(Integer spendingSalaryId);
    public List<SpendingSalary> getSpendingSalariesByContractStageId(Integer contractStageId);

    public SpendingSalary updateSpendingSalary(SpendingSalary spendingSalary);
    public SpendingSalary createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary);

    public void deleteSpendingSalaryById(Integer SpendingSalaryId);
    public void deleteAllSpendingSalariesByContractStageId(Integer contractStageId);
}
