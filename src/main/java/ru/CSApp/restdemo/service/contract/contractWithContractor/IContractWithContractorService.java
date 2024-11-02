package ru.CSApp.restdemo.service.contract.contractWithContractor;

import ru.CSApp.restdemo.model.ContractWithContractor;

import java.util.List;

public interface IContractWithContractorService {
    public List<ContractWithContractor> getContractsWithContractorsByContractId(Integer contractId);
    public ContractWithContractor getContractWithContractorById(Integer contractWitContractorId);
    public List<ContractWithContractor> getAllContractsWithContractors();

    public ContractWithContractor createContractWithContractorForContract(Integer contractorId, ContractWithContractor contractWithContractor);

    public void deleteContractWithContractorById(Integer ContractWithContractorId);
    public void deleteAllContractsWithContractorsByContractId(Integer contractId);
}
