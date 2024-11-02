package ru.CSApp.restdemo.service.contract;

import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.model.*;
import ru.CSApp.restdemo.repository.contract.IContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContractService implements IContractService {

    IContractRepository contractRepository;

    ContractService(IContractRepository contractRepository){
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract createContract(Contract contract) {
        // contractRepository.save(contract);
        for (ContractStage stage : contract.getStages()) {
            stage.setContract(contract);
        }
        for (ContractWithContractor contractWithContractor : contract.getSubContracts()) {
            contractWithContractor.setContract(contract);
        }
        contractRepository.save(contract);
        return contract;
    }

    @Override
    public Contract updateContract(Contract contract) {
        contractRepository.save(contract);
        return contract;
    }

    @Override
    public List<Contract> getAllContracts() {
        var contracts = contractRepository.findAll();
        return contracts;
    }

    @Override
    public Contract getContractById(Integer id) {
        if(contractRepository.findById(id).isEmpty())
            throw new ContractNotFoundException("There is no object with such Id");
        return contractRepository.findById(id).get();
    }

    @Override
    public List<Contract> getContractByName(Map<String, Object> data) {
        String name = data.get("name").toString();
        List<Contract> contracts = contractRepository.findByName(name);
        return contracts;
    }

    @Override
    public void deleteContractById(Integer contractId) {
        if(contractRepository.findById(contractId).isEmpty())
            throw new ContractNotFoundException("Contract no found with such Id");
        contractRepository.deleteById(contractId);
    }

    @Override
    public void deleteAllContracts() {
        contractRepository.deleteAll();
    }
}
