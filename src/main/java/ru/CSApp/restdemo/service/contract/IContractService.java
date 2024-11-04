package ru.CSApp.restdemo.service.contract;

import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.ContractDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IContractService {
    public Contract createContract(ContractDto contract);
    public Contract updateContract(ContractDto contract);

    public List<Contract> getAllContracts();
    public Contract getContractById(Integer id);
    public List<Contract> getContractByName(Map<String, Object> data);

    public void deleteContractById(Integer contractId);
    public void deleteAllContracts();

    public void save(Contract contract);
    public List<Contract> findByPlanStartDateAfterAndPlanEndDateBefore(LocalDate start, LocalDate end);
}
