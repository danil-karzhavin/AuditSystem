package ru.CSApp.restdemo.service.contractor;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.model.ContractWithContractor;
import ru.CSApp.restdemo.model.Contractor;
import ru.CSApp.restdemo.model.INN;
import ru.CSApp.restdemo.repository.contractor.IContractorRepository;
import ru.CSApp.restdemo.repository.contractor.inn.IInnRepository;

import java.util.List;

@Service
public class ContractorService implements IContractorService {
    IContractorRepository contractorRepository;
    IInnRepository innRepository;

    public ContractorService(IContractorRepository contractorRepository, IInnRepository innRepository) {
        this.contractorRepository = contractorRepository;
        this.innRepository = innRepository;
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
        INN inn = contractor.getInn();
        innRepository.save(inn);
        //inn.setContractorId(contractor.getId());

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
