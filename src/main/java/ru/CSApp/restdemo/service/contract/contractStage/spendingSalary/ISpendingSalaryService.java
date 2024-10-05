package ru.CSApp.restdemo.service.contract.contractStage.spendingSalary;

import ru.CSApp.restdemo.model.SpendingSalary;

import java.util.List;

public interface ISpendingSalaryService {
    public SpendingSalary getSpendingSalaryById(Integer spendingSalaryId);
    public List<SpendingSalary> getSpendingSalariesByContractStageId(Integer contractStageId);

    public SpendingSalary updateSpendingSalary(SpendingSalary spendingSalary);
    public void createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary);

    public Integer deleteSpendingSalaryById(Integer SpendingSalaryId);
    public Integer deleteAllSpendingSalariesByContractStageId(Integer contractStageId);
}
