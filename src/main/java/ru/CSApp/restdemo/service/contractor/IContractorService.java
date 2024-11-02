package ru.CSApp.restdemo.service.contractor;

import ru.CSApp.restdemo.model.contractor.Contractor;
import ru.CSApp.restdemo.model.contractor.ContractorDto;

import java.util.List;


public interface IContractorService {
    public List<Contractor> getAllContractors();
    public Contractor getContractorById(Integer contractorId);

    public Contractor createContractor(ContractorDto contractorDto);
    public Contractor updateContractor(ContractorDto contractorDto);

    public void deleteContractorById(Integer contractorId);
    public void deleteAllContractors(); // только для тестов

    public void save(Contractor contractor);
}