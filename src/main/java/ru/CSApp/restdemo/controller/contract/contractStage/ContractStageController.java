package ru.CSApp.restdemo.controller.contract.contractStage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;
import ru.CSApp.restdemo.service.contract.contractExcelWriter.ExportExcelService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        return ResponseEntity.ok(contractStageService.getContractStageById(contractStageId));
    }

    @GetMapping("/byContract/{contractId}")
    public ResponseEntity<Object> getContractStagesByContractId(@PathVariable("contractId") Integer contractId){
        return ResponseEntity.ok(contractStageService.getContractStagesByContractId(contractId));
    }

    @PostMapping("/newContractStage/{contractId}")
    public ResponseEntity<Object> createContractStageForContract(@PathVariable("contractId") Integer contractId, @RequestBody ContractStage contractStage){
        return ResponseEntity.ok(contractStageService.createContractStageForContract(contractId, contractStage));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateContractStage(@RequestBody ContractStage contractStage){
        return ResponseEntity.ok(contractStageService.updateContactStage(contractStage));
    }

    @DeleteMapping("/{contractStageId}")
    public ResponseEntity<Object> deleteContractStageById(@PathVariable("contractStageId") Integer contractStageId){
        contractStageService.deleteContractStageById(contractStageId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/byContract/{contractId}")
    public ResponseEntity<Object> deleteContractStageByContractId(@PathVariable("contractId") Integer contractId){
        contractStageService.deleteAllContractStagesByContractId(contractId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getExcelFile/{contractId}")
    public void getExcelFileByContract(@PathVariable("contractId") Integer contractId, HttpServletResponse response){
        try {
            String fileName = exportExcelService.getContractStagesFileName(contractId);
            byte[] fileData = exportExcelService.createExcelFileContractStages(contractId);

            // Установка заголовков ответа
            response.setContentType(exportExcelService.determineContentType());
            response.setHeader("Content-Disposition", exportExcelService.determineContentDisposition(fileName));

            // Запись данных в HTTP-ответ
            response.getOutputStream().write(fileData);
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (ContractNotFoundException e){
            System.out.println(e);
            //return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND); // 404
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
