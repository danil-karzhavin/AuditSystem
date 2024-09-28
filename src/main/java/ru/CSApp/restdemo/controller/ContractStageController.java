package ru.CSApp.restdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contractStage.IContractStageService;

import java.util.List;

@RestController
@RequestMapping("/contractStages")
public class ContractStageController {
    IContractService contractService;

    public ContractStageController(IContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/{contractStageId}")
    public ResponseEntity<Object> getContractStageById(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseHandler.responseBuilder("Requested Contract Stage Details are given here",
                HttpStatus.OK, contractService.getContractStageById(contractStageId));
    }

    @GetMapping("/byContract/{contractId}")
    public ResponseEntity<Object> getContractStagesByContractId(@PathVariable("contractId") Integer contractId){
        return ResponseHandler.responseBuilder("Requested Contract Stage Details are given here",
                HttpStatus.OK, contractService.getContractStagesByContractId(contractId));
    }

    @PostMapping("/newContractStage/{contractId}")
    public String createContractStageForContract(@PathVariable("contractId") Integer contractId, @RequestBody ContractStage contractStage){
        contractService.createContractStageForContract(contractId, contractStage);
        return "Create Contract Stage For Contract Successfully";
    }

    @PostMapping("/newSpendingMaterial/{contractStageId}")
    public String createSpendingMaterialForContractStage(@PathVariable("contractStageId") Integer contractStageId, @RequestBody SpendingMaterial spendingMaterial){
        contractService.createSpendingMaterialForContractStage(contractStageId, spendingMaterial);
        return "Create Spending Material For Contract Stage Successfully";
    }

}
