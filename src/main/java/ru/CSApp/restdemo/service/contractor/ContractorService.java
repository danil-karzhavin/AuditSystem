package ru.CSApp.restdemo.service.contractor;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.model.ContractWithContractor;
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
        Contractor contractor = contractorRepository.findById(contractorId).get();
        return contractor;
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
    public Integer deleteContractorById(Integer contractorId) {
        contractorRepository.deleteById(contractorId);
        return contractorId;
    }
}
