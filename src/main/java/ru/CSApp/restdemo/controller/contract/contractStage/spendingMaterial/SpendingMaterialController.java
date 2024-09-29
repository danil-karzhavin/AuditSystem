package ru.CSApp.restdemo.controller.contract.contractStage.spendingMaterial;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial.ISpendingMaterialService;

@RestController
@RequestMapping("/spendingMaterials")
public class SpendingMaterialController {
    ISpendingMaterialService spendingMaterialService;

    public SpendingMaterialController(ISpendingMaterialService spendingMaterialService) {
        this.spendingMaterialService = spendingMaterialService;
    }

    @GetMapping("/{spendingMaterialId}")
    public ResponseEntity<Object> getSpendingMaterialById(@PathVariable("spendingMaterialId") Integer spendingMaterialId){
        return ResponseHandler.responseBuilder("Requested Spending Material Details are given here",
                HttpStatus.OK, spendingMaterialService.getSpendingMaterialById(spendingMaterialId));
    }

    @GetMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> getSpendingMaterialsByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseHandler.responseBuilder("Requested Spending Material Details are given here",
                HttpStatus.OK, spendingMaterialService.getSpendingMaterialsByContractStageId(contractStageId));
    }
}