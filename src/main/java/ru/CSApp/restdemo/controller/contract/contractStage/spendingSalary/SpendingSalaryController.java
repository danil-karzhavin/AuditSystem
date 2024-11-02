package ru.CSApp.restdemo.controller.contract.contractStage.spendingSalary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalary;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalaryDto;
import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;
import ru.CSApp.restdemo.service.contract.contractStage.spendingSalary.ISpendingSalaryService;

@RestController
@RequestMapping("/spendingSalaries")
public class SpendingSalaryController {
    ISpendingSalaryService spendingSalaryService;
    IContractStageService contractStageService;

    public SpendingSalaryController(ISpendingSalaryService spendingSalaryService, IContractStageService contractStageService) {
        this.spendingSalaryService = spendingSalaryService;
        this.contractStageService  = contractStageService;
    }

    @GetMapping("/{spendingSalaryId}")
    public ResponseEntity<Object> getSpendingSalaryById(@PathVariable("spendingSalaryId") Integer spendingSalaryId){
        return ResponseEntity.ok(spendingSalaryService.getSpendingSalaryById(spendingSalaryId));
    }

    @GetMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> getSpendingSalariesByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseEntity.ok(spendingSalaryService.getSpendingSalariesByContractStageId(contractStageId));
    }

    @PostMapping("/newSpendingSalary/{contractStageId}")
    public ResponseEntity<Object> createSpendingSalaryForContractStage(@PathVariable("contractStageId") Integer contractStageId, @RequestBody SpendingSalaryDto spendingSalaryDto){
        return ResponseEntity.ok(spendingSalaryService.createSpendingSalaryForContractStage(contractStageId, spendingSalaryDto));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateSpendingSalary(@RequestBody SpendingSalaryDto spendingSalaryDto){
        return ResponseEntity.ok(spendingSalaryService.updateSpendingSalary(spendingSalaryDto));
    }

    @DeleteMapping("/{spendingSalaryId}")
    public ResponseEntity<Object> deleteSpendingSalaryById(@PathVariable("spendingSalaryId") Integer spendingSalaryId){
        spendingSalaryService.deleteSpendingSalaryById(spendingSalaryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> deleteAllSpendingSalariesByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        spendingSalaryService.deleteAllSpendingSalariesByContractStageId(contractStageId);
        return ResponseEntity.noContent().build();
    }
}
