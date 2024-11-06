package ru.CSApp.restdemo.service.contractor;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contractor.ContractorNotFoundException;
import ru.CSApp.restdemo.model.contractor.Contractor;
import ru.CSApp.restdemo.model.contractor.ContractorDto;
import ru.CSApp.restdemo.repository.contractor.IContractorRepository;

import java.util.List;
import java.util.Optional;

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
        Optional<Contractor> contractorOptional = contractorRepository.findById(contractorId);

        if (contractorOptional.isEmpty()) {
            throw new ContractorNotFoundException("Not found contractor with such Id");
        }
        return contractorOptional.get();
    }

    @Override
    public Contractor createContractor(ContractorDto contractorDto) {
        Contractor contractor = new Contractor();

        Optional.ofNullable(contractorDto.getName()).ifPresent(contractor::setName);
        Optional.ofNullable(contractorDto.getAddress()).ifPresent(contractor::setAddress);
        Optional.ofNullable(contractorDto.getInn()).ifPresent(contractor::setInn);

        contractorRepository.save(contractor);
        return contractor;
    }

    @Override
    public Contractor updateContractor(ContractorDto contractorDto) {
        Contractor contractor = getContractorById(contractorDto.getId());

        Optional.ofNullable(contractorDto.getName()).ifPresent(contractor::setName);
        Optional.ofNullable(contractorDto.getAddress()).ifPresent(contractor::setAddress);
        Optional.ofNullable(contractorDto.getInn()).ifPresent(contractor::setInn);

        contractorRepository.save(contractor);
        return contractor;
    }

    @Override
    public void deleteContractorById(Integer contractorId) {
        boolean exists = contractorRepository.existsById(contractorId);
        if (!exists) {
            throw new ContractorNotFoundException("Not found contractor with such Id");
        }
        contractorRepository.deleteById(contractorId);
    }

    @Override
    public void deleteAllContractors() {
        contractorRepository.deleteAll();
    }

    @Override
    public void save(Contractor contractor) {
        contractorRepository.save(contractor);
    }
}
