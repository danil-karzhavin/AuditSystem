package ru.CSApp.restdemo.service.contract;

import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.model.ContractStage;

import java.util.List;
import java.util.Map;

public interface IContractService {
    public Contract createContract(Contract contract);
    public Contract updateContract(Contract contract);

    public List<Contract> getAllContracts();
    public Contract getContractById(Integer id);
    public List<Contract> getContractByName(Map<String, Object> data);

    public void deleteContractById(Integer contractId);
    public void deleteAllContracts();
}
