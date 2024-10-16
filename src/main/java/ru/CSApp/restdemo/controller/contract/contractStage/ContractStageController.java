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
    public void getExcelFileByContract(@PathVariable("contractId") Integer contractId, HttpServletResponse response){
        try {
            String fileName = exportExcelService.getFileNameForClient(contractId);
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
