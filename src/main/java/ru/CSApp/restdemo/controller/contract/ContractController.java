package ru.CSApp.restdemo.controller.contract;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.ContractDto;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contract.contractExcelWriter.ExportExcelService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    IContractService contractService;
    ExportExcelService exportExcelService;

    public ContractController(IContractService contractService, ExportExcelService exportExcelService) {
        this.contractService = contractService;
        this.exportExcelService = exportExcelService;
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<Object> getContractById(@PathVariable("contractId") Integer contractId) {
        return ResponseEntity.ok(contractService.getContractById(contractId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Contract>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @GetMapping("/byName")
    public ResponseEntity<Object> getContractByName(@RequestBody Map<String, Object> dataName) {
        return ResponseEntity.ok(contractService.getContractByName(dataName));
    }

    @PostMapping("/")
    public ResponseEntity<Object> createContract(@RequestBody ContractDto contractDto) {
        return ResponseEntity.ok(contractService.createContract(contractDto));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateContract(@RequestBody ContractDto contractDto) {
        return ResponseEntity.ok(contractService.updateContract(contractDto));
    }

    @DeleteMapping("/{contractId}")
    public ResponseEntity<Object> deleteContractById(@PathVariable("contractId") Integer contractId) {
        contractService.deleteContractById(contractId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteAllContracts() {
        contractService.deleteAllContracts();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getExcelFile")
    public void getExcelFileByContract(@RequestBody Map<String, Object> dates, HttpServletResponse response) throws IOException, ContractNotFoundException  {
        String fileName = exportExcelService.getContractsFileName();
        byte[] fileData = exportExcelService.createExcelFileContracts(dates);

        // Установка заголовков ответа
        response.setContentType(exportExcelService.determineContentType());
        response.setHeader("Content-Disposition", exportExcelService.determineContentDisposition(fileName));

        // Запись данных в HTTP-ответ
        response.getOutputStream().write(fileData);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}