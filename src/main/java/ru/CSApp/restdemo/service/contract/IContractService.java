package ru.CSApp.restdemo.service.contract;

import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.model.SpendingSalary;

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

//    public ContractStage getContractStageById(Integer contractStageId);
//    public List<ContractStage> getContractStagesByContractId(Integer contractId);
//
//    public void createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial);
//    public void createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary);
}
