package ru.CSApp.restdemo.service.contract.contractStage;

import ru.CSApp.restdemo.model.ContractStage;

import java.util.List;


public interface IContractStageService {
    public ContractStage getContractStageById(Integer contractStageId);
    public List<ContractStage> getContractStagesByContractId(Integer contractId);

    public void createContractStageForContract(Integer contractId, ContractStage contractStage);

    public ContractStage updateContactStage(ContractStage contractStage);
    public void deleteContractStageFromContract(Integer contractId, ContractStage contractStage);

    public Integer deleteContractStageById(Integer contractStageId);
    public Integer deleteAllContractStagesByContractId(Integer contractId);
}
