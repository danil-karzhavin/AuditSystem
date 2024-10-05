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
    public String createContract(Contract contract) {
        // contractRepository.save(contract);

        for (ContractStage stage : contract.getStages()) {
            stage.setContract(contract);
        }
        for (ContractWithContractor contractWithContractor : contract.getSubContracts()) {
            contractWithContractor.setContract(contract);
        }
        return contractRepository.save(contract).toString();
    }

    @Override
    public String updateContract(Contract contract) {
        contractRepository.save(contract);
        return "Success";
    }

    @Override
    public List<Contract> getAllContracts() {
        var contracts = contractRepository.findAll();
        return contracts;
    }

    @Override
    public Contract getContractById(Integer id) {
        try{
            if(contractRepository.findById(id).isEmpty())
                throw new ContractNotFoundException("There is no object with such Id");
            return contractRepository.findById(id).get();
        }
        catch(ContractNotFoundException ex){
            return null;
        }
    }

    @Override
    public List<Contract> getContractByName(Map<String, Object> data) {
        String name = data.get("name").toString();
        List<Contract> contracts = contractRepository.findByName(name);
        return contracts;
    }

    @Override
    public Integer deleteContractById(Integer contractId) {
        contractRepository.deleteById(contractId);
        return contractId;
    }

    @Override
    public Integer deleteAllContracts() {
        contractRepository.deleteAll();
        return 0;
    }
}
