package ru.CSApp.restdemo.controller.contractor.inn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.Inn;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contractor.inn.IInnService;

@RestController
@RequestMapping("/inn")
public class InnController {
    IInnService innService;

    public InnController(IInnService innService) {
        this.innService = innService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllInns(){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, innService.getAllInns());
    }

    @GetMapping("/{contractorId}")
    public ResponseEntity<Object> getInnByContractorId(@PathVariable("contractorId") Integer contractorId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, innService.getInnByContractId(contractorId));
    }

    @PostMapping("/")
    public ResponseEntity<Object> createInnForContractor(@PathVariable("contractorId") Integer contractorId, @RequestBody Inn inn){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, innService.createInnForContractor(contractorId, inn));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateInn(@RequestBody Inn inn){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, innService.updateInn(inn));
    }

    @DeleteMapping("/{contractorId}")
    public ResponseEntity<Object> deleteInnByContractorId(@PathVariable("contractorId") Integer contractorId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, innService.deleteInnByContractorId(contractorId));
    }
}
