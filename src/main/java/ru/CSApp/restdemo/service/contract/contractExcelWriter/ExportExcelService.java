package ru.CSApp.restdemo.service.contract.contractExcelWriter;

import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.model.Contract;
import ru.CSApp.restdemo.model.ContractStage;
import ru.CSApp.restdemo.model.ContractWithContractor;
import ru.CSApp.restdemo.model.IContractable;
import ru.CSApp.restdemo.service.contract.ContractService;
import ru.CSApp.restdemo.service.contract.contractStage.ContractStageService;
import ru.CSApp.restdemo.service.contract.contractWithContractor.ContractWithContractorService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExportExcelService {
    private final String fileDirectoryContracts = "C:/Users/karzh/source/repos/Java/CSApp/excelFiles/contracts";
    private final String fileDirectoryContractStages = "C:/Users/karzh/source/repos/Java/CSApp/excelFiles/contractStages";
    //private final String fileDirectory = "C:/Users/karzh/source//repos/javaProjects/AuditSystem/excelFiles";
    private final Path fileStorageLocation = Paths.get("/path/to/your/files").toAbsolutePath().normalize();
    ContractService contractService;
    ContractStageService contractStageService;
    ContractWithContractorService contractWithContractorService;

    public ExportExcelService(ContractService contractService, ContractWithContractorService contractWithContractorService, ContractStageService contractStageService) {
        this.contractService = contractService;
        this.contractWithContractorService = contractWithContractorService;
        this.contractStageService = contractStageService;
    }

    public String determineContentType() {
        // Возврат типа контента для Excel
        return "application/vnd.ms-excel";
    }

    public String determineContentDisposition(String filename) {
        // Генерация заголовка Content-Disposition
        return "attachment; filename=\"" + filename + "\"";
    }

    public byte[] getExcelFile(String fileName) throws IOException{
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("File not found"); //
        }

        return Files.readAllBytes(file.toPath());
    }

    public String getFullFileNameContractStages(Integer contractId){
        return fileDirectoryContractStages + "/" + getFileNameForClient(contractId);
    }
    public String getFileNameForClient(Integer contractId){
        return String.format("ExportStages Contract_%d.xlsx", contractId);
    }

    public String getFullFileNameContracts(){
        return fileDirectoryContracts + getFileNameContractsForClient();
    }
    public String getFileNameContractsForClient(){
        return String.format("ExportContracts.xlsx");
    }

    public byte[] createExcelFileContractStages(Integer contractId) throws IOException, ContractNotFoundException {
        List<ContractStage> contractStages = contractStageService.getContractStagesByContractId(contractId);

        String fileName = getFullFileNameContractStages(contractId);
        ContractStagesExcelWriter.writeContractStagesToExcel(contractStages, fileName);

        return getExcelFile(fileName);
    }

    public byte[] createExcelFileContracts(Map<String, Object> dates) throws IOException, ContractNotFoundException{
        LocalDate start = LocalDate.parse(dates.get("startDate").toString());
        LocalDate end = LocalDate.parse(dates.get("endDate").toString());

        ArrayList<IContractable> contractables = new ArrayList<>();

        List<Contract> contracts = contractService.getAllContracts();
        for (Contract contract : contracts){
            if (contract == null)
                continue;
            if(contract.getPlanStartDate().isAfter(start) && contract.getPlanStartDate().isBefore(end)
                    && (contract.getPlanEndDate().isBefore(end) && contract.getPlanEndDate().isAfter(start)))
                contractables.add(contract);
        }

        List<ContractWithContractor> contractWithContractors = contractWithContractorService.getAllContractsWithContractors();
        for (ContractWithContractor contractWithContractor : contractWithContractors){
            if (contractWithContractor == null)
                continue;
            if(contractWithContractor.getPlanStartDate().isAfter(start) && contractWithContractor.getPlanStartDate().isBefore(end)
                    && (contractWithContractor.getPlanEndDate().isBefore(end) && contractWithContractor.getPlanEndDate().isAfter(start)))
                contractables.add(contractWithContractor);
        }

        String fileName = getFullFileNameContracts();

        ContractExcelWriter.writeContractsToExcel(contractables, fileName);

        return getExcelFile(fileName);
    }
}
