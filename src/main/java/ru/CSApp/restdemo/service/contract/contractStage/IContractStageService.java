package ru.CSApp.restdemo.service.contract.contractStage;

import ru.CSApp.restdemo.model.ContractStage;

import java.util.List;


public interface IContractStageService {
    public ContractStage getContractStageById(Integer contractStageId);
    public List<ContractStage> getContractStagesByContractId(Integer contractId);

    public ContractStage createContractStageForContract(Integer contractId, ContractStage contractStage);

    public ContractStage updateContactStage(ContractStage contractStage);
    // public void deleteContractStageByContractId(Integer contractId, ContractStage contractStage);

    public void deleteContractStageById(Integer contractStageId);
    public void deleteAllContractStagesByContractId(Integer contractId);
}
