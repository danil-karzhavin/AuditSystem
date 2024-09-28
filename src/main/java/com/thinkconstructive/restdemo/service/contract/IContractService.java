package com.thinkconstructive.restdemo.service.contract;

import com.thinkconstructive.restdemo.model.Contract;
import com.thinkconstructive.restdemo.model.ContractStage;
import com.thinkconstructive.restdemo.model.SpendingMaterial;
import com.thinkconstructive.restdemo.model.SpendingSalary;

import java.util.List;

public interface IContractService {
    public String createContract(Contract contract);
    public String updateContract(Contract contract);
    public String deleteContract(Integer id);

    public List<Contract> getAllContracts();
    public Contract getContractById(Integer id);
    public Contract getContractByName(String name);

    public void createContractStageForContract(Integer id, ContractStage contractStage);
    public void removeContractStageFromContract(Contract contract, ContractStage contractStage);

    public String createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial);
    public String createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary);
}
