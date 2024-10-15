package ru.CSApp.restdemo.service.contract.contractExcelWriter;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.service.contract.ContractService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.io.File;

@Service
public class ExportExcelService {
    //private final String fileDirectory = "C:/Users/karzh/source/repos/Java/CSApp/excelFiles";
    private final String fileDirectory = "C:/Users/karzh/source//repos/javaProjects/AuditSystem/excelFiles";
    private final Path fileStorageLocation = Paths.get("/path/to/your/files").toAbsolutePath().normalize();
    ContractService contractService;

    public ExportExcelService(ContractService contractService) {
        this.contractService = contractService;
    }

    public String determineContentType() {
        // Возврат типа контента для Excel
        return "application/vnd.ms-excel";
    }

    public String determineContentDisposition(String filename) {
        // Генерация заголовка Content-Disposition
        return "attachment; filename=\"" + filename + "\"";
    }

    public String getFileNameByContractId(Integer contractId){
        return fileDirectory + "/" + getFileNameForClient(contractId);
    }
    public String getFileNameForClient(Integer contractId){
        return String.format("ExcelExportContract_%d.xlsx", contractId);
    }

    public byte[] getExcelFile(Integer contractId) throws IOException, ContractNotFoundException {
        Contract contract = contractService.getContractById(contractId);
        if(contract == null)
            throw new ContractNotFoundException("There is no object with such Id");

        String fileName = getFileNameByContractId(contractId);
        ContractExcelWriter.writeContractToExcel(contract, fileName);

        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("File not found"); //
        }

        return Files.readAllBytes(file.toPath());
    }

    public void createExcelFile(Contract contract, String fileName) throws IOException{

    }
}
