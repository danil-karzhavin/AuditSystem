package ru.CSApp.restdemo.service.contractor;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contractor.ContractorNotFoundException;
import ru.CSApp.restdemo.model.Contractor;
import ru.CSApp.restdemo.model.Inn;
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
        try{
            if(contractorRepository.findById(contractorId).isEmpty())
                throw new ContractorNotFoundException("There is no object with such Id");
            return contractorRepository.findById(contractorId).get();
        }
        catch (ContractorNotFoundException ex){
            return null;
        }
    }

    @Override
    public Contractor createContractor(Contractor contractor) {
        innRepository.save(contractor.getInn()); // id в обеих моделях null, еще не созданы в репозитории
        // нужно сохранять каждую модель в своем репозитории, связи фреймворк простроит сам на основе аннотоций и класса contractor

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
