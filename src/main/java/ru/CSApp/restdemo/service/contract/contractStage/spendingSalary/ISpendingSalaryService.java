package ru.CSApp.restdemo.service.contract.contractStage.spendingSalary;

import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalary;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalaryDto;

import java.util.List;

public interface ISpendingSalaryService {
    public SpendingSalary getSpendingSalaryById(Integer spendingSalaryId);
    public List<SpendingSalary> getSpendingSalariesByContractStageId(Integer contractStageId);

    public SpendingSalary updateSpendingSalary(SpendingSalaryDto spendingSalaryDto);
    public SpendingSalary createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalaryDto spendingSalaryDto);

    public void deleteSpendingSalaryById(Integer SpendingSalaryId);
    public void deleteAllSpendingSalariesByContractStageId(Integer contractStageId);
}
