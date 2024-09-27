package com.thinkconstructive.restdemo.service.contractStage;

import com.thinkconstructive.restdemo.model.ContractStage;
import com.thinkconstructive.restdemo.model.SpendingMaterial;
import com.thinkconstructive.restdemo.model.SpendingSalary;

public interface IContractStageService {
    public String createContractStage(ContractStage contractStage);
    public String addSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial);
    public String addSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary);
}
