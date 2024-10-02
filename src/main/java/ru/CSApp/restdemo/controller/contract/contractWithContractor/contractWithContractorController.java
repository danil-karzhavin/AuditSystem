package ru.CSApp.restdemo.controller.contract.contractWithContractor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.ContractWithContractor;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contract.contractWithContractor.ContractWithContractorService;
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
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, subContractService.getContractWithContractorsByContractId(contractId));
    }

    @GetMapping("/{contractWithContractorId}")
    public ResponseEntity<Object> getSubContractById(@PathVariable("contractWithContractorId") Integer contractWithContractorId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, subContractService.getContractWithContractorById(contractWithContractorId));
    }

    @PostMapping("/{contractId}")
    public ResponseEntity<Object> createSubContract(@PathVariable("contractId") Integer contractId, @RequestBody ContractWithContractor contractWithContractor){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, subContractService.createContractWithContractorForContract(contractId, contractWithContractor));
    }

    @DeleteMapping("/{contractWithContractorId}")
    public ResponseEntity<Object> deleteContractWithContractorById(@PathVariable("contractWithContractorId") Integer contractWithContractorId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, subContractService.deleteContractWithContractorById(contractWithContractorId));
    }

    @DeleteMapping("/byContract/{contractId}")
    public ResponseEntity<Object> deleteAllContractsWithContractorsByContractId(@PathVariable("contractId") Integer contractId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, subContractService.deleteAllContractsWithContractorsByContractId(contractId));
    }
}