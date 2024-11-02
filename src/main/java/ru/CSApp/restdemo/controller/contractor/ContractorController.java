package ru.CSApp.restdemo.controller.contractor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.Contractor;
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
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractorService.getAllContractors());
    }

    @GetMapping("/{contractorId}")
    public ResponseEntity<Object> getContractorById(@PathVariable("contractorId") Integer contractorId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractorService.getContractorById(contractorId));
    }

    @PostMapping("/")
    public ResponseEntity<Object> createContractor(@RequestBody Contractor contractor){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractorService.createContractor(contractor));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateContractor(@RequestBody Contractor contractor){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractorService.updateContractor(contractor));
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
