package ru.CSApp.restdemo.service.contract.contractWithContractor;

import org.springframework.stereotype.Service;
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
    public List<ContractWithContractor> getContractWithContractorsByContractId(Integer contractId) {
        var contractWithContractors = contractWithContractorRepository.findByContractId(contractId);
        return contractWithContractors;
    }

    @Override
    public ContractWithContractor getContractWithContractorById(Integer contractWitContractorId) {
        ContractWithContractor contractWithContractor = contractWithContractorRepository.findById(contractWitContractorId).get();
        return contractWithContractor;
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
        contractWithContractorRepository.deleteById(contractWithContractorId);
        return 0;
    }

    @Override
    public Integer deleteAllContractsWithContractorsByContractId(Integer contractId) {
        for (var obj : getContractWithContractorsByContractId(contractId)){
            contractWithContractorRepository.deleteById(obj.getId());
        }
        return 0;
    }
}
