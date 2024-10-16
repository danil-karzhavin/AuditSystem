package ru.CSApp.restdemo.service.contract.contractWithContractor;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.contractWithContractor.ContractWithContractorNotFoundException;
import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.model.ContractWithContractor;
import ru.CSApp.restdemo.repository.contract.IContractRepository;
import ru.CSApp.restdemo.repository.contractor.IContractWithContractorRepository;

import java.util.List;

@Service
public class ContractWithContractorService implements IContractWithContractorService{
    IContractWithContractorRepository contractWithContractorRepository;
    IContractRepository contractRepository;

    public ContractWithContractorService(IContractWithContractorRepository contractWithContractorRepository, IContractRepository contractRepository) {
        this.contractWithContractorRepository = contractWithContractorRepository;
        this.contractRepository = contractRepository;
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
        try{
            if(contractWithContractorRepository.findById(contractWitContractorId).isEmpty())
                throw new ContractWithContractorNotFoundException("There is no object with such Id");
            return contractWithContractorRepository.findById(contractWitContractorId).get();
        }
        catch (ContractWithContractorNotFoundException ex){
            return null;
        }
    }

    @Override
    public Integer createContractWithContractorForContract(Integer contractId, ContractWithContractor contractWithContractor) {
        Contract contract = contractRepository.findById(contractId).get();

        contractWithContractor.setContract(contract);
        contractWithContractor.setContractId(contractId);

        contract.getSubContracts().add(contractWithContractor);
        contractRepository.save(contract);
        return 0;
    }

    @Override
    public Integer deleteContractWithContractorById(Integer contractWithContractorId) {
        try{
            if(contractWithContractorRepository.findById(contractWithContractorId).isEmpty())
                throw new ContractWithContractorNotFoundException("There is no object with such Id");
            contractWithContractorRepository.deleteById(contractWithContractorId);
            return contractWithContractorId;
        }
        catch(ContractWithContractorNotFoundException ex){
            return null;
        }
    }

    @Override
    public Integer deleteAllContractsWithContractorsByContractId(Integer contractId) {
        for (var obj : getContractsWithContractorsByContractId(contractId)){
            contractWithContractorRepository.deleteById(obj.getId());
        }
        return 0;
    }
}
