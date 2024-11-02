package ru.CSApp.restdemo.service.contract.contractStage;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.contractStage.ContractStageNotFoundException;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStageDto;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterial;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalary;
import ru.CSApp.restdemo.repository.contract.IContractRepository;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contractor.IContractorService;

import java.util.List;
import java.util.Optional;

@Service
public class ContractStageService implements IContractStageService{
    IContractStageRepository contractStageRepository;
    IContractService contractService;

    public ContractStageService(IContractStageRepository IContractStageRepository, IContractService contractService) {
        this.contractStageRepository = IContractStageRepository;
        this.contractService = contractService;
    }

    @Override
    public ContractStage getContractStageById(Integer contractStageId){
        if(contractStageRepository.findById(contractStageId).isEmpty())
            throw new ContractStageNotFoundException("Not found contract stage with such Id");
        return contractStageRepository.findById(contractStageId).get();
    }

    @Override
    public List<ContractStage> getContractStagesByContractId(Integer contractId) {
        var contractStages = contractStageRepository.findByContractId(contractId);
        return contractStages;
    }

    @Override
    public ContractStage createContractStageForContract(Integer contractId, ContractStageDto contractStageDto){
        ContractStage contractStage = new ContractStage();

        Optional.ofNullable(contractStageDto.getName()).ifPresent(contractStage::setName);
        Optional.ofNullable(contractStageDto.getPlanStartDate()).ifPresent(contractStage::setPlanStartDate);
        Optional.ofNullable(contractStageDto.getPlanEndDate()).ifPresent(contractStage::setPlanEndDate);
        Optional.ofNullable(contractStageDto.getActualStartDate()).ifPresent(contractStage::setActualStartDate);
        Optional.ofNullable(contractStageDto.getActualEndDate()).ifPresent(contractStage::setActualEndDate);
        Optional.ofNullable(contractStageDto.getMonetaryValue()).ifPresent(contractStage::setMonetaryValue);

        Contract contract = contractService.getContractById(contractId);
        contractStage.setContract(contract); // устанавливаем связи
        contractStage.setContractId(contractId);

        for (SpendingMaterial spendingMaterial : contractStage.getSpendingMaterials()){
            spendingMaterial.setContractStage(contractStage);
            //spendingMaterial.setContractStageId(contractStage.getId());
        }

        for (SpendingSalary spendingSalary : contractStage.getSpendingSalaries()){
            spendingSalary.setContractStage(contractStage);
            //spendingSalary.setContractStageId(contractStage.getId());
        }

        contractStageRepository.save(contractStage);
        contract.getStages().add(contractStage); // добавляем объект в коллекцию
        contractService.save(contract); // Поскольку каскадные операции включены, ContractStage будет автоматически сохранен
        return contractStage;
    }

    @Override
    public ContractStage updateContactStage(ContractStageDto contractStageDto) {
        ContractStage contractStage = getContractStageById(contractStageDto.getId());

        Optional.ofNullable(contractStageDto.getName()).ifPresent(contractStage::setName);
        Optional.ofNullable(contractStageDto.getPlanStartDate()).ifPresent(contractStage::setPlanStartDate);
        Optional.ofNullable(contractStageDto.getPlanEndDate()).ifPresent(contractStage::setPlanEndDate);
        Optional.ofNullable(contractStageDto.getActualStartDate()).ifPresent(contractStage::setActualStartDate);
        Optional.ofNullable(contractStageDto.getActualEndDate()).ifPresent(contractStage::setActualEndDate);
        Optional.ofNullable(contractStageDto.getMonetaryValue()).ifPresent(contractStage::setMonetaryValue);

        contractStageRepository.save(contractStage);
        return contractStage;
    }

    @Override
    public void deleteContractStageById(Integer contractStageId) {
        if(contractStageRepository.findById(contractStageId).isEmpty())
            throw new ContractStageNotFoundException("Not found contract stage with such Id");
        contractStageRepository.deleteById(contractStageId);
    }

    @Override
    public void deleteAllContractStagesByContractId(Integer contractId) {
        for(var obj : getContractStagesByContractId(contractId)){
            contractStageRepository.deleteById(obj.getId());
        }
    }

    @Override
    public void save(ContractStage contractStage) {
        contractStageRepository.save(contractStage);
    }
}
