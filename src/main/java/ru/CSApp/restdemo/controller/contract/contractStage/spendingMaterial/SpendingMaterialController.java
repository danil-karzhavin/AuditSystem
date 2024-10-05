package ru.CSApp.restdemo.controller.contract.contractStage.spendingMaterial;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.SpendingMaterial;
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

    @PostMapping("/newSpendingMaterial/{contractStageId}")
    public String createSpendingMaterialForContractStage(@PathVariable("contractStageId") Integer contractStageId, @RequestBody SpendingMaterial spendingMaterial){
        spendingMaterialService.createSpendingMaterialForContractStage(contractStageId, spendingMaterial);
        return "Create Spending Material For Contract Stage Successfully";
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateSpendingMaterial(@RequestBody SpendingMaterial spendingMaterial){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, spendingMaterialService.updateSpendingMaterial(spendingMaterial));
    }

    @DeleteMapping("/{spendingMaterialId}")
    public ResponseEntity<Object> deleteSpendingMaterialById(@PathVariable("spendingMaterialId") Integer spendingMaterialId){
        return ResponseHandler.responseBuilder("Deleted Spending Material",
                HttpStatus.OK, spendingMaterialService.deleteSpendingMaterialById(spendingMaterialId));
    }

    @DeleteMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> deleteAllSpendingMaterialByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseHandler.responseBuilder("Deleted Spending Material by Contract Stage Id",
                HttpStatus.OK, spendingMaterialService.deleteAllSpendingMaterialByContractStageId(contractStageId));
    }
}