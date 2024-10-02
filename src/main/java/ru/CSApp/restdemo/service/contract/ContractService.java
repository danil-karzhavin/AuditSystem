package ru.CSApp.restdemo.service.contract;

import ru.CSApp.restdemo.exception.ContractNotFoundException;
import ru.CSApp.restdemo.model.*;
import ru.CSApp.restdemo.repository.contract.IContractRepository;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService implements IContractService {

    IContractRepository IContractRepository;
    IContractStageRepository IContractStageRepository;

    ContractService(IContractRepository IContractRepository, IContractStageRepository IContractStageRepository){
        this.IContractRepository = IContractRepository;
        this.IContractStageRepository = IContractStageRepository;
    }

    @Override
    public String createContract(Contract contract) {
        // contractRepository.save(contract);

        // скорее всего эти коллекции будут всегда пусты, но на всякий случай
        for (ContractStage stage : contract.getStages()) {
            stage.setContract(contract);
        }
        for (ContractWithContractor contractWithContractor : contract.getSubContracts()) {
            contractWithContractor.setContract(contract);
        }
        return IContractRepository.save(contract).toString();
    }

    @Override
    public String updateContract(Contract contract) {
        IContractRepository.save(contract);
        return "Success";
    }

    @Override
    public List<Contract> getAllContracts() {
        var contracts = IContractRepository.findAll();
        return contracts;
    }

    @Override
    public Contract getContractById(Integer id) {
        if(IContractRepository.findById(id).isEmpty()){
            throw new ContractNotFoundException("There is no object with such Id");
        }
        Contract contract = IContractRepository.findById(id).get();

        return contract;
    }

    @Override
    public Contract getContractByName(String name) {
        Contract contract = IContractRepository.findByName(name);
        return contract;
    }

    public void createContractStageForContract(Integer contractId, ContractStage contractStage){
        Contract contract = getContractById(contractId);
        contractStage.setContract(contract); // устанавливаем связи
        contractStage.setContractId(contractId);

        for (SpendingMaterial spendingMaterial : contractStage.getSpendingMaterials())
            spendingMaterial.setContractStage(contractStage);

        for (SpendingSalary spendingSalary : contractStage.getSpendingSalaries())
            spendingSalary.setContractStage(contractStage);

        contract.getStages().add(contractStage); // добавляем объект в коллекцию
        IContractRepository.save(contract); // Поскольку каскадные операции включены, ContractStage будет автоматически сохранен
    }

    public void removeContractStageFromContract(Contract contract, ContractStage contractStage) {
        contract.getStages().remove(contractStage);
        IContractRepository.save(contract); // ContractStage будет автоматически удален благодаря orphanRemoval = true
    }

    @Override
    public Integer deleteContractById(Integer contractId) {
        IContractRepository.deleteById(contractId);
        return contractId;
    }

    @Override
    public Integer deleteAllContracts() {
        IContractRepository.deleteAll();
        return 0;
    }

    //    @Override
//    public ContractStage getContractStageById(Integer contractStageId){
//        if(contractStageRepository.findById(contractStageId).isEmpty())
//            throw new ContractNotFoundException("There is no object with such Id"); // добавить свое исключение
//
//        ContractStage contractStage = contractStageRepository.findById(contractStageId).get();
//        return contractStage;
//    }
//
//    @Override
//    public List<ContractStage> getContractStagesByContractId(Integer contractId) {
//        // var contract = contractRepository.findById(contractId);
//        var contractStages = contractStageRepository.findByContractId(contractId);
//        return contractStages;
//    }
//
//    @Override
//    public void createSpendingMaterialForContractStage(Integer contractStageId, SpendingMaterial spendingMaterial) {
//        ContractStage contractStage = getContractStageById(contractStageId);
//        spendingMaterial.setContractStage(contractStage);
//        spendingMaterial.setContractStageId(contractStageId);
//
//        contractStage.getSpendingMaterials().add(spendingMaterial);
//        contractStageRepository.save(contractStage);
//    }
//
//    @Override
//    public void createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalary spendingSalary) {
//        ContractStage contractStage = getContractStageById(contractStageId);
//        spendingSalary.setContractStage(contractStage);
//        spendingSalary.setContractStageId(contractStageId);
//
//        contractStage.getSpendingSalaries().add(spendingSalary);
//        contractStageRepository.save(contractStage);
//    }
}
