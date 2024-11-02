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
        return ResponseEntity.ok(spendingMaterialService.getSpendingMaterialById(spendingMaterialId));
    }

    @GetMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> getSpendingMaterialsByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseEntity.ok(spendingMaterialService.getSpendingMaterialsByContractStageId(contractStageId));
    }

    @PostMapping("/newSpendingMaterial/{contractStageId}")
    public ResponseEntity<Object> createSpendingMaterialForContractStage(@PathVariable("contractStageId") Integer contractStageId, @RequestBody SpendingMaterial spendingMaterial){
        return ResponseEntity.ok(spendingMaterialService.createSpendingMaterialForContractStage(contractStageId, spendingMaterial));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateSpendingMaterial(@RequestBody SpendingMaterial spendingMaterial){
        return ResponseEntity.ok(spendingMaterialService.updateSpendingMaterial(spendingMaterial));
    }

    @DeleteMapping("/{spendingMaterialId}")
    public ResponseEntity<Object> deleteSpendingMaterialById(@PathVariable("spendingMaterialId") Integer spendingMaterialId){
        spendingMaterialService.deleteSpendingMaterialById(spendingMaterialId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/byContractStage/{contractStageId}")
    public ResponseEntity<Object> deleteAllSpendingMaterialByContractStageId(@PathVariable("contractStageId") Integer contractStageId){
        spendingMaterialService.deleteAllSpendingMaterialByContractStageId(contractStageId);
        return ResponseEntity.noContent().build();
    }
}