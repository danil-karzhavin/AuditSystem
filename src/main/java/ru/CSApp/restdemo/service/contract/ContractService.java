package ru.CSApp.restdemo.service.contract;

import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.ContractDto;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;
import ru.CSApp.restdemo.repository.contract.IContractRepository;
import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;
import ru.CSApp.restdemo.service.contract.contractWithContractor.IContractWithContractorService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContractService implements IContractService {

    IContractRepository contractRepository;

    public ContractService(IContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract createContract(ContractDto contractDto) {
        Contract contract = new Contract();
        Optional.ofNullable(contractDto.getName()).ifPresent(contract::setName);
        Optional.ofNullable(contractDto.getType().toString()).ifPresent(contract::setType);
        Optional.ofNullable(contractDto.getPlanStartDate()).ifPresent(contract::setPlanStartDate);
        Optional.ofNullable(contractDto.getPlanEndDate()).ifPresent(contract::setPlanEndDate);
        Optional.ofNullable(contractDto.getActualStartDate()).ifPresent(contract::setActualStartDate);
        Optional.ofNullable(contractDto.getActualEndDate()).ifPresent(contract::setActualEndDate);
        Optional.ofNullable(contractDto.getMonetaryValue()).ifPresent(contract::setMonetaryValue);

        contractRepository.save(contract);
        return contract;
    }

    @Override
    public Contract updateContract(ContractDto contractDto) {
        Contract contract = getContractById(contractDto.getId());

        Optional.ofNullable(contractDto.getName()).ifPresent(contract::setName);
        Optional.ofNullable(contractDto.getType().toString()).ifPresent(contract::setType);
        Optional.ofNullable(contractDto.getPlanStartDate()).ifPresent(contract::setPlanStartDate);
        Optional.ofNullable(contractDto.getPlanEndDate()).ifPresent(contract::setPlanEndDate);
        Optional.ofNullable(contractDto.getActualStartDate()).ifPresent(contract::setActualStartDate);
        Optional.ofNullable(contractDto.getActualEndDate()).ifPresent(contract::setActualEndDate);
        Optional.ofNullable(contractDto.getMonetaryValue()).ifPresent(contract::setMonetaryValue);

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
        if (contractRepository.findById(id).isEmpty())
            throw new ContractNotFoundException("Not found contract with such Id");
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
        if (contractRepository.findById(contractId).isEmpty())
            throw new ContractNotFoundException("Not found contract with such Id");
        contractRepository.deleteById(contractId);
    }

    @Override
    public void deleteAllContracts() {
        contractRepository.deleteAll();
    }

    @Override
    public void save(Contract contract) {
        contractRepository.save(contract);
    }
}
