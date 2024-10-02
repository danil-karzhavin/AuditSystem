package ru.CSApp.restdemo.service.contract.contractWithContractor;

import ru.CSApp.restdemo.model.ContractWithContractor;

import java.util.List;

public interface IContractWithContractorService {
    public List<ContractWithContractor> getContractWithContractorsByContractId(Integer contractId);
    public ContractWithContractor getContractWithContractorById(Integer contractWitContractorId);

    public Integer createContractWithContractorForContract(Integer contractId, ContractWithContractor contractWithContractor);

    public Integer deleteContractWithContractorById(Integer ContractWithContractorId);
    public Integer deleteAllContractsWithContractorsByContractId(Integer contractId);
}
