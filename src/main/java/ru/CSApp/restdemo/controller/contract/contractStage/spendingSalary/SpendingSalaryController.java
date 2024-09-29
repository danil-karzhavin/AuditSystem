package ru.CSApp.restdemo.controller.contract.contractStage.spendingSalary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial.ISpendingMaterialService;
import ru.CSApp.restdemo.service.contract.contractStage.spendingSalary.ISpendingSalaryService;

@RestController
@RequestMapping("/spendingSalaries")
public class SpendingSalaryController {
    ISpendingSalaryService spendingSalaryService;

    public SpendingSalaryController(ISpendingSalaryService spendingSalaryService) {
        this.spendingSalaryService = spendingSalaryService;
    }

    @GetMapping("/{spendingSalaryId}")
    public ResponseEntity<Object> getSpendingMaterialById(@PathVariable("spendingSalaryId") Integer spendingSalaryId){
        return ResponseHandler.responseBuilder("Requested Spending Salary Details are given here",
                HttpStatus.OK, spendingSalaryService.getSpendingSalaryById(spendingSalaryId));
    }

    @GetMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> getSpendingSalariesByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseHandler.responseBuilder("Requested Spending Salary Details are given here",
                HttpStatus.OK, spendingSalaryService.getSpendingSalariesByContractStageId(contractStageId));
    }
}
