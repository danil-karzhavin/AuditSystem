package ru.CSApp.restdemo.service.contractor;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contractor.ContractorNotFoundException;
import ru.CSApp.restdemo.model.Contractor;
import ru.CSApp.restdemo.repository.contractor.IContractorRepository;

import java.util.List;

@Service
public class ContractorService implements IContractorService {
    IContractorRepository contractorRepository;

    public ContractorService(IContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public List<Contractor> getAllContractors() {
        var contractors = contractorRepository.findAll();
        return contractors;
    }

    @Override
    public Contractor getContractorById(Integer contractorId) {
        if(contractorRepository.findById(contractorId).isEmpty())
            throw new ContractorNotFoundException("Contract not found with such Id");
        return contractorRepository.findById(contractorId).get();
    }

    @Override
    public Contractor createContractor(Contractor contractor) {
        contractorRepository.save(contractor);
        return contractor;
    }

    @Override
    public Contractor updateContractor(Contractor contractor) {
        contractorRepository.save(contractor);
        return contractor;
    }

    @Override
    public void deleteContractorById(Integer contractorId) {
        if(contractorRepository.findById(contractorId).isEmpty())
            throw new ContractorNotFoundException("Contract not found with such Id");
        contractorRepository.deleteById(contractorId);
    }

    @Override
    public void deleteAllContractors() {
        contractorRepository.deleteAll();
    }
}
