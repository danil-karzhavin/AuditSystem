package ru.CSApp.restdemo.controller.contract.contractStage.spendingSalary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.SpendingSalary;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contract.contractStage.spendingSalary.ISpendingSalaryService;

@RestController
@RequestMapping("/spendingSalaries")
public class SpendingSalaryController {
    ISpendingSalaryService spendingSalaryService;

    public SpendingSalaryController(ISpendingSalaryService spendingSalaryService) {
        this.spendingSalaryService = spendingSalaryService;
    }

    @GetMapping("/{spendingSalaryId}")
    public ResponseEntity<Object> getSpendingSalaryById(@PathVariable("spendingSalaryId") Integer spendingSalaryId){
        return ResponseHandler.responseBuilder("Requested Spending Salary Details are given here",
                HttpStatus.OK, spendingSalaryService.getSpendingSalaryById(spendingSalaryId));
    }

    @GetMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> getSpendingSalariesByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseHandler.responseBuilder("Requested Spending Salary Details are given here",
                HttpStatus.OK, spendingSalaryService.getSpendingSalariesByContractStageId(contractStageId));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateSpendingSalary(@RequestBody SpendingSalary spendingSalary){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, spendingSalaryService.updateSpendingSalary(spendingSalary));
    }

    @DeleteMapping("/{spendingSalaryId}")
    public ResponseEntity<Object> deleteSpendingSalaryById(@PathVariable("spendingSalaryId") Integer spendingSalaryId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, spendingSalaryService.deleteSpendingSalaryById(spendingSalaryId));
    }

    @DeleteMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> deleteAllSpendingSalariesByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, spendingSalaryService.deleteAllSpendingSalariesByContractStageId(contractStageId));
    }
}
