package ru.CSApp.restdemo.service.contract.contractStage;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.exception.contract.contractStage.ContractStageNotFoundException;
import ru.CSApp.restdemo.exception.contract.contractStage.spendingSalary.SpendingSalaryNotFoundException;
import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.model.SpendingSalary;
import ru.CSApp.restdemo.repository.contract.IContractRepository;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;

import java.util.List;
@Service
public class ContractStageService implements IContractStageService{
    IContractStageRepository contractStageRepository;
    IContractRepository contractRepository;

    public ContractStageService(IContractStageRepository IContractStageRepository, IContractRepository contractRepository) {
        this.contractStageRepository = IContractStageRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public ContractStage getContractStageById(Integer contractStageId){
        try{
            if(contractStageRepository.findById(contractStageId).isEmpty())
                throw new ContractStageNotFoundException("There is no object with such Id");
            return contractStageRepository.findById(contractStageId).get();
        }
        catch (ContractStageNotFoundException ex){
            return null;
        }
    }

    @Override
    public List<ContractStage> getContractStagesByContractId(Integer contractId) {
        var contractStages = contractStageRepository.findByContractId(contractId);
        return contractStages;
    }

    @Override
    public void createContractStageForContract(Integer contractId, ContractStage contractStage){
        Contract contract = contractRepository.findById(contractId).get();
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
        contractRepository.save(contract); // Поскольку каскадные операции включены, ContractStage будет автоматически сохранен
    }

    @Override
    public void deleteContractStageFromContract(Integer contractId, ContractStage contractStage) {
        Contract contract = contractRepository.findById(contractId).get();
        contract.getStages().remove(contractStage);
        contractRepository.save(contract); // ContractStage будет автоматически удален благодаря orphanRemoval = true
    }

    @Override
    public ContractStage updateContactStage(ContractStage contractStage) {
        contractStageRepository.save(contractStage);
        return contractStage;
    }

    @Override
    public Integer deleteContractStageById(Integer contractStageId) {
        try{
            if(contractStageRepository.findById(contractStageId).isEmpty())
                throw new ContractStageNotFoundException("There is no object with such Id");
            contractStageRepository.deleteById(contractStageId);
            return contractStageId;
        }
        catch(ContractStageNotFoundException ex){
            return null;
        }
    }

    @Override
    public Integer deleteAllContractStagesByContractId(Integer contractId) {
        for(var obj : getContractStagesByContractId(contractId)){
            contractStageRepository.deleteById(obj.getId());
        }
        return 0;
    }
}
