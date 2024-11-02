package ru.CSApp.restdemo.service.contract.contractWithContractor;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.contractWithContractor.ContractWithContractorNotFoundException;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractorDto;
import ru.CSApp.restdemo.model.contractor.Contractor;
import ru.CSApp.restdemo.repository.contractor.IContractWithContractorRepository;
import ru.CSApp.restdemo.repository.contractor.IContractorRepository;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contractor.IContractorService;

import java.util.List;
import java.util.Optional;

@Service
public class ContractWithContractorService implements IContractWithContractorService{
    IContractWithContractorRepository contractWithContractorRepository;
    IContractService contractService;
    IContractorService contractorService;

    public ContractWithContractorService(IContractWithContractorRepository contractWithContractorRepository, IContractService contractService, IContractorService contractorService) {
        this.contractWithContractorRepository = contractWithContractorRepository;
        this.contractService = contractService;
        this.contractorService = contractorService;
    }

    @Override
    public List<ContractWithContractor> getContractsWithContractorsByContractId(Integer contractId) {
        var contractWithContractors = contractWithContractorRepository.findByContractId(contractId);
        return contractWithContractors;
    }

    @Override
    public List<ContractWithContractor> getAllContractsWithContractors() {
        var contractWithContractors = contractWithContractorRepository.findAll();
        return contractWithContractors;
    }

    @Override
    public ContractWithContractor getContractWithContractorById(Integer contractWitContractorId) {
        if(contractWithContractorRepository.findById(contractWitContractorId).isEmpty())
            throw new ContractWithContractorNotFoundException("Not found sub contract with such Id");
        return contractWithContractorRepository.findById(contractWitContractorId).get();
    }

    @Override
    public ContractWithContractor createContractWithContractorForContract(Integer contractorId, ContractWithContractorDto contractWithContractorDto) {
        ContractWithContractor contractWithContractor = new ContractWithContractor();

        Optional.ofNullable(contractWithContractorDto.getName()).ifPresent(contractWithContractor::setName);
        Optional.ofNullable(contractWithContractorDto.getType()).ifPresent(contractWithContractor::setType);
        Optional.ofNullable(contractWithContractorDto.getPlanStartDate()).ifPresent(contractWithContractor::setPlanStartDate);
        Optional.ofNullable(contractWithContractorDto.getPlanEndDate()).ifPresent(contractWithContractor::setPlanEndDate);
        Optional.ofNullable(contractWithContractorDto.getActualStartDate()).ifPresent(contractWithContractor::setActualStartDate);
        Optional.ofNullable(contractWithContractorDto.getActualEndDate()).ifPresent(contractWithContractor::setActualEndDate);
        Optional.ofNullable(contractWithContractorDto.getMonetaryValue()).ifPresent(contractWithContractor::setMonetaryValue);
        Optional.ofNullable(contractWithContractorDto.getContractId()).ifPresent(contractWithContractor::setContractId);


        Contract contract = contractService.getContractById(contractWithContractor.getContractId());
        contract.getSubContracts().add(contractWithContractor);
        contractWithContractor.setContract(contract);

        Contractor contractor = contractorService.getContractorById(contractorId);
        contractor.setContractWithContractor(contractWithContractor);
        contractWithContractor.setContractor(contractor);

        contractWithContractorRepository.save(contractWithContractor);
        contractService.save(contract);
        contractorService.save(contractor);
        return contractWithContractor;
    }

    @Override
    public void deleteContractWithContractorById(Integer contractWithContractorId) {
        if(contractWithContractorRepository.findById(contractWithContractorId).isEmpty())
            throw new ContractWithContractorNotFoundException("Not found sub contract with such Id");
        contractWithContractorRepository.deleteById(contractWithContractorId);
    }

    @Override
    public void deleteAllContractsWithContractorsByContractId(Integer contractId) {
        for (var obj : getContractsWithContractorsByContractId(contractId)){
            contractWithContractorRepository.deleteById(obj.getId());
        }
    }
}
