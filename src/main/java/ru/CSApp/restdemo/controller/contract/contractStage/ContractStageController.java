package ru.CSApp.restdemo.controller.contract.contractStage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.SpendingMaterial;
import ru.CSApp.restdemo.model.SpendingSalary;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;
import ru.CSApp.restdemo.service.contract.contractStage.exportExcel.ExportExcelService;

import java.io.IOException;
//import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;


@RestController
@RequestMapping("/contractStages")
public class ContractStageController {
    IContractService contractService;
    IContractStageService contractStageService;
    ExportExcelService exportExcelService;

    public ContractStageController(IContractService contractService, IContractStageService contractStageService, ExportExcelService exportExcelService) {
        this.contractService = contractService;
        this.contractStageService = contractStageService;
        this.exportExcelService = exportExcelService;
    }

    @GetMapping("/{contractStageId}")
    public ResponseEntity<Object> getContractStageById(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseHandler.responseBuilder("Requested Contract Stage Details are given here",
                HttpStatus.OK, contractStageService.getContractStageById(contractStageId));
    }

    @GetMapping("/byContract/{contractId}")
    public ResponseEntity<Object> getContractStagesByContractId(@PathVariable("contractId") Integer contractId){
        return ResponseHandler.responseBuilder("Requested Contract Stage Details are given here",
                HttpStatus.OK, contractStageService.getContractStagesByContractId(contractId));
    }

    @PostMapping("/newContractStage/{contractId}")
    public String createContractStageForContract(@PathVariable("contractId") Integer contractId, @RequestBody ContractStage contractStage){
        contractStageService.createContractStageForContract(contractId, contractStage);
        return "Create Contract Stage For Contract Successfully";
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateContractStage(@RequestBody ContractStage contractStage){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractStageService.updateContactStage(contractStage));
    }

    @DeleteMapping("/{contractStageId}")
    public ResponseEntity<Object> deleteContractStageById(@PathVariable("contractStageId") Integer contractStageId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractStageService.deleteContractStageById(contractStageId));
    }

    @DeleteMapping("/byContract/{contractId}")
    public ResponseEntity<Object> deleteContractStageByContractId(@PathVariable("contractId") Integer contractId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, contractStageService.deleteAllContractStagesByContractId(contractId));
    }

    @GetMapping("/getExcelFile/{contractId}")
    public ResponseEntity<byte[]> getExcelFileByContract(@PathVariable("contractId") Integer contractId){
        try {
            //String fileName = contractId.toString(); // это будет потом
            String fileName = "hello.xlsx"; // это будет потом
            byte[] fileData = exportExcelService.getFile(fileName);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
