package ru.CSApp.restdemo.service.contractor;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.model.Contractor;

import java.util.List;


public interface IContractorService {
    public List<Contractor> getAllContractors();
    public Contractor getContractorById(Integer contractorId);

    public Contractor createContractor(Contractor contractor);
    public Contractor updateContractor(Contractor contractor);

    public Integer deleteContractorById(Integer contractorId);
}