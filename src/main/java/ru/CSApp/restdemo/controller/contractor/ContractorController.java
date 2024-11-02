package ru.CSApp.restdemo.controller.contractor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.contractor.Contractor;
import ru.CSApp.restdemo.model.contractor.ContractorDto;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contractor.IContractorService;

@RestController
@RequestMapping("/contractors")
public class ContractorController {
    IContractorService contractorService;

    public ContractorController(IContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllContractors(){
        return ResponseEntity.ok(contractorService.getAllContractors());
    }

    @GetMapping("/{contractorId}")
    public ResponseEntity<Object> getContractorById(@PathVariable("contractorId") Integer contractorId){
        return ResponseEntity.ok(contractorService.getContractorById(contractorId));
    }

    @PostMapping("/")
    public ResponseEntity<Object> createContractor(@RequestBody ContractorDto contractorDto){
        return ResponseEntity.ok(contractorService.createContractor(contractorDto));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateContractor(@RequestBody ContractorDto contractorDto){
        return ResponseEntity.ok(contractorService.updateContractor(contractorDto));
    }

    @DeleteMapping("/{contractId}")
    public ResponseEntity<Object> deleteContractor(@PathVariable("contractId") Integer contractId){
        contractorService.deleteContractorById(contractId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/") // только для тестов
    public ResponseEntity<Object> deleteAllContractor(){
        contractorService.deleteAllContractors();
        return ResponseEntity.noContent().build();
    }
}
