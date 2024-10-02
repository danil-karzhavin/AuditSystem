package ru.CSApp.restdemo.service.contract.contractStage;

import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.model.SpendingSalary;

import java.util.List;


public interface IContractStageService {
    public ContractStage getContractStageById(Integer contractStageId);
    public List<ContractStage> getContractStagesByContractId(Integer contractId);

    public void createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial);
    public void createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary);

    public ContractStage updateContactStage(ContractStage contractStage);

    public Integer deleteContractStageById(Integer contractStageId);
    public Integer deleteAllContractStagesByContractId(Integer contractId);
}
