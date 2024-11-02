package ru.CSApp.restdemo.controller.contract.contractStage.spendingMaterial;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterial;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterialDto;
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
    public ResponseEntity<Object> createSpendingMaterialForContractStage(@PathVariable("contractStageId") Integer contractStageId, @RequestBody SpendingMaterialDto spendingMaterialDto){
        return ResponseEntity.ok(spendingMaterialService.createSpendingMaterialForContractStage(contractStageId, spendingMaterialDto));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateSpendingMaterial(@RequestBody SpendingMaterialDto spendingMaterialDto){
        return ResponseEntity.ok(spendingMaterialService.updateSpendingMaterial(spendingMaterialDto));
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