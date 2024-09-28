package com.thinkconstructive.restdemo.controller;

import com.thinkconstructive.restdemo.model.Contract;
import com.thinkconstructive.restdemo.model.ContractStage;
import com.thinkconstructive.restdemo.response.ResponseHandler;
import com.thinkconstructive.restdemo.service.contract.IContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    IContractService contractService;

    public ContractController(IContractService contractService){
        this.contractService = contractService;
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<Object> getContractDetails(@PathVariable("contractId") Integer contractId){
        return ResponseHandler.responseBuilder("Requested Contract Details are given here",
                HttpStatus.OK, contractService.getContractById(contractId));
    }

    @GetMapping("/")
    public List<Contract> getAllContractDetails()
    {
        return contractService.getAllContracts();
    }

    @PostMapping("/")
    public String createContractDetails(@RequestBody Contract contract)
    {
        contractService.createContract(contract);
        return "Contract Created Successfully";
    }

    @PutMapping("/")
    public String updateContractDetails(@RequestBody Contract contract)
    {
        contractService.updateContract(contract);
        return "Contract Updated Successfully";
    }

    @DeleteMapping("/{contractId}")
    public String deleteContractDetails(@PathVariable("contractId") Integer contractId){
        contractService.deleteContract(contractId);
        return "Contract Deleted Successfully";
    }

    @PostMapping("/{contractId}")
    public String createContractStageForContract(@PathVariable("contractId") Integer contractId, @RequestBody ContractStage contractStage){
        contractService.createContractStageForContract(contractId, contractStage);
        return "Create Contract Stage For Contract Successfully";
    }
}