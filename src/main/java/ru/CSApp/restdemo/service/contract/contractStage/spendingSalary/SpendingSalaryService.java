package ru.CSApp.restdemo.service.contract.contractStage.spendingSalary;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.contractStage.spendingSalary.SpendingSalaryNotFoundException;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalary;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalaryDto;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;
import ru.CSApp.restdemo.repository.contract.stage.spendingSalary.ISpendingSalaryRepository;
import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;

import java.util.List;
import java.util.Optional;

@Service
public class SpendingSalaryService implements ISpendingSalaryService{
    ISpendingSalaryRepository spendingSalaryRepository;
    IContractStageService contractStageService;

    public SpendingSalaryService(ru.CSApp.restdemo.repository.contract.stage.spendingSalary.ISpendingSalaryRepository spendingSalaryRepository, IContractStageService contractStageService) {
        this.spendingSalaryRepository = spendingSalaryRepository;
        this.contractStageService = contractStageService;
    }

    @Override
    public SpendingSalary getSpendingSalaryById(Integer spendingSalaryId) {
        if (spendingSalaryRepository.findById(spendingSalaryId).isEmpty())
            throw new SpendingSalaryNotFoundException("Not found spending salary with such Id");
        return spendingSalaryRepository.findById(spendingSalaryId).get();
    }

    @Override
    public List<SpendingSalary> getSpendingSalariesByContractStageId(Integer contractStageId) {
        var spendingSalaries = spendingSalaryRepository.findByContractStageId(contractStageId);
        return spendingSalaries;
    }

    @Override
    public SpendingSalary createSpendingSalaryForContractStage(Integer contractStageId, SpendingSalaryDto spendingSalaryDto) {
        SpendingSalary spendingSalary = new SpendingSalary();

        Optional.ofNullable(spendingSalaryDto.getName()).ifPresent(spendingSalary::setName);
        Optional.ofNullable(spendingSalaryDto.getSurname()).ifPresent(spendingSalary::setSurname);
        Optional.ofNullable(spendingSalaryDto.getMonetaryValue()).ifPresent(spendingSalary::setMonetaryValue);

        ContractStage contractStage = contractStageService.getContractStageById(contractStageId);
        spendingSalary.setContractStage(contractStage);
        spendingSalary.setContractStageId(contractStageId);

        contractStage.getSpendingSalaries().add(spendingSalary);
        spendingSalaryRepository.save(spendingSalary);
        contractStageService.save(contractStage);
        return spendingSalary;
    }

    @Override
    public SpendingSalary updateSpendingSalary(SpendingSalaryDto spendingSalaryDto) {
        SpendingSalary spendingSalary = getSpendingSalaryById(spendingSalaryDto.getId());

        Optional.ofNullable(spendingSalaryDto.getName()).ifPresent(spendingSalary::setName);
        Optional.ofNullable(spendingSalaryDto.getSurname()).ifPresent(spendingSalary::setSurname);
        Optional.ofNullable(spendingSalaryDto.getMonetaryValue()).ifPresent(spendingSalary::setMonetaryValue);

        spendingSalaryRepository.save(spendingSalary);
        return spendingSalary;
    }

    @Override
    public void deleteSpendingSalaryById(Integer spendingSalaryId) {
        if(spendingSalaryRepository.findById(spendingSalaryId).isEmpty())
            throw new SpendingSalaryNotFoundException("Not found spending salary with such Id");
        spendingSalaryRepository.deleteById(spendingSalaryId);
    }

    @Override
    public void deleteAllSpendingSalariesByContractStageId(Integer contractStageId) {
        for(var obj : getSpendingSalariesByContractStageId(contractStageId)){
            spendingSalaryRepository.deleteById(obj.getId());
        }
    }
}
