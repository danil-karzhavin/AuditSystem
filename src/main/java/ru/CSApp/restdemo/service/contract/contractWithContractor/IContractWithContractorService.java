package ru.CSApp.restdemo.service.contract.contractWithContractor;

import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractorDto;

import java.time.LocalDate;
import java.util.List;

public interface IContractWithContractorService {
    public List<ContractWithContractor> getContractsWithContractorsByContractId(Integer contractId);
    public ContractWithContractor getContractWithContractorById(Integer contractWitContractorId);
    public List<ContractWithContractor> getAllContractsWithContractors();

    public ContractWithContractor createContractWithContractorForContract(Integer contractorId, ContractWithContractorDto contractWithContractorDto);

    public void deleteContractWithContractorById(Integer ContractWithContractorId);
    public void deleteAllContractsWithContractorsByContractId(Integer contractId);

    public List<ContractWithContractor> findByPlanStartDateAfterAndPlanEndDateBefore(LocalDate start, LocalDate end);
}
