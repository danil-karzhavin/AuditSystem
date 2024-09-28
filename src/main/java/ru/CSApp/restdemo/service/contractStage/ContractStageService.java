package ru.CSApp.restdemo.service.contractStage;

import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.model.SpendingSalary;

public class ContractStageService implements IContractStageService{
    @Override
    public String createContractStage(ContractStage contractStage) {
        return "";
    }

    @Override
    public String addSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial) {
        return "";
    }

    @Override
    public String addSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary) {
        return "";
    }
}
