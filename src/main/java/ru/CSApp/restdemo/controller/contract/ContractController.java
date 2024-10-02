package ru.CSApp.restdemo.controller.contract;

import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contract.IContractService;
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
    public ResponseEntity<Object> updateContractDetails(@RequestBody Contract contract)
    {
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractService.updateContract(contract));
    }

    @DeleteMapping("/{contractId}")
    public ResponseEntity<Object> deleteContractDetails(@PathVariable("contractId") Integer contractId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractService.deleteContractById(contractId));
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteAllContracts(){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractService.deleteAllContracts());
    }
}