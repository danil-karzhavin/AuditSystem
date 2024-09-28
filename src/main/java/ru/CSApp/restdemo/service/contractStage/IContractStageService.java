package ru.CSApp.restdemo.service.contractStage;

import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.model.SpendingSalary;

public interface IContractStageService {
    public String createContractStage(ContractStage contractStage);
    public String addSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial);
    public String addSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary);
}
