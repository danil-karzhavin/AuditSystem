package ru.CSApp.restdemo.controller.contract.contractWithContractor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractorDto;
import ru.CSApp.restdemo.service.contract.contractWithContractor.IContractWithContractorService;

@RestController
@RequestMapping("subContracts")
public class contractWithContractorController {
    IContractWithContractorService subContractService;

    public contractWithContractorController(IContractWithContractorService subContractService) {
        this.subContractService = subContractService;
    }

    @GetMapping("byContract/{contractId}")
    public ResponseEntity<Object> getAllSubContractsByContractId(@PathVariable("contractId") Integer contractId){
        return ResponseEntity.ok(subContractService.getContractsWithContractorsByContractId(contractId));
    }

    @GetMapping("/{contractWithContractorId}")
    public ResponseEntity<Object> getSubContractById(@PathVariable("contractWithContractorId") Integer contractWithContractorId){
        return ResponseEntity.ok(subContractService.getContractWithContractorById(contractWithContractorId));
    }

    @PostMapping("/{contractorId}")
    public ResponseEntity<Object> createSubContract(@PathVariable("contractorId") Integer contractorId, @RequestBody ContractWithContractorDto contractWithContractorDto){
        return ResponseEntity.ok(subContractService.createContractWithContractorForContract(contractorId, contractWithContractorDto));
    }

    @DeleteMapping("/{contractWithContractorId}")
    public ResponseEntity<Object> deleteContractWithContractorById(@PathVariable("contractWithContractorId") Integer contractWithContractorId){
        subContractService.deleteContractWithContractorById(contractWithContractorId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/byContract/{contractId}")
    public ResponseEntity<Object> deleteAllContractsWithContractorsByContractId(@PathVariable("contractId") Integer contractId){
        subContractService.deleteAllContractsWithContractorsByContractId(contractId);
        return ResponseEntity.noContent().build();
    }
}
