package ru.CSApp.restdemo.service.contract;

import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.model.ContractStage;

import java.util.List;
import java.util.Map;

public interface IContractService {
    public String createContract(Contract contract);
    public String updateContract(Contract contract);

    public List<Contract> getAllContracts();
    public Contract getContractById(Integer id);
    public List<Contract> getContractByName(Map<String, Object> data);

    public Integer deleteContractById(Integer contractId);
    public Integer deleteAllContracts();
}
