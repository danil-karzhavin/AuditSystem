package ru.CSApp.restdemo.service.contract.contractStage;

import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStageDto;

import java.util.List;


public interface IContractStageService {
    public ContractStage getContractStageById(Integer contractStageId);
    public List<ContractStage> getContractStagesByContractId(Integer contractId);

    public ContractStage createContractStageForContract(Integer contractId, ContractStageDto contractStageDto);

    public ContractStage updateContactStage(ContractStageDto contractStageDto);

    public void deleteContractStageById(Integer contractStageId);
    public void deleteAllContractStagesByContractId(Integer contractId);

    public void save(ContractStage contractStage);
}
