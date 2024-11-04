package ru.CSApp.restdemo.service.contract.contractExcelWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;
import ru.CSApp.restdemo.model.contract.IContractable;
import ru.CSApp.restdemo.repository.contract.IContractRepository;
import ru.CSApp.restdemo.repository.contractor.IContractWithContractorRepository;
import ru.CSApp.restdemo.service.contract.ContractService;
import ru.CSApp.restdemo.service.contract.contractStage.ContractStageService;
import ru.CSApp.restdemo.service.contract.contractWithContractor.ContractWithContractorService;

import java.nio.file.Files;
import java.io.IOException;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExportExcelService {
    public static final String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";

    @Value("${excelFile.directory.contracts}")
    private String fileDirectoryContracts;
    @Value("${excelFile.directory.contractStages}")
    private String fileDirectoryContractStages;

    ContractService contractService;
    ContractStageService contractStageService;
    ContractWithContractorService contractWithContractorService;

    public ExportExcelService(ContractService contractService, ContractWithContractorService contractWithContractorService,
                              ContractStageService contractStageService) {
        this.contractService = contractService;
        this.contractWithContractorService = contractWithContractorService;
        this.contractStageService = contractStageService;
    }

    public String determineContentType() {
        // Возврат типа контента для Excel
        return EXCEL_CONTENT_TYPE;
    }

    public String determineContentDisposition(String filename) {
        // Генерация заголовка Content-Disposition
        return String.format("attachment; filename=\"%s\"", filename);
    }

    public byte[] getExcelFile(String fileName) throws IOException{
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("File not found");
        }
        return Files.readAllBytes(file.toPath());
    }

    public String getContractStagesFilePath(Integer contractId){
        return fileDirectoryContractStages + "/" + getContractStagesFileName(contractId);
    }
    public String getContractStagesFileName(Integer contractId){
        return String.format("ExportStages Contract_%d.xlsx", contractId);
    }

    public String getContractsFilePath(){
        return fileDirectoryContracts + getContractsFileName();
    }
    public String getContractsFileName(){
        return String.format("ExportContracts.xlsx");
    }

    public byte[] createExcelFileContractStages(Integer contractId) throws IOException, ContractNotFoundException {
        List<ContractStage> contractStages = contractStageService.getContractStagesByContractId(contractId);

        String fileName = getContractStagesFilePath(contractId);
        ContractStagesExcelWriter.writeContractStagesToExcel(contractStages, fileName);

        return getExcelFile(fileName);
    }

    public byte[] createExcelFileContracts(Map<String, Object> dates) throws IOException, ContractNotFoundException{
        LocalDate start = LocalDate.parse(dates.get("startDate").toString());
        LocalDate end = LocalDate.parse(dates.get("endDate").toString());

        List<Contract> contracts = contractService.findByPlanStartDateAfterAndPlanEndDateBefore(start, end);
        List<ContractWithContractor> contractWithContractors = contractWithContractorService.findByPlanStartDateAfterAndPlanEndDateBefore(start, end);

        ArrayList<IContractable> contractables = new ArrayList<>();
        contractables.addAll(contracts);
        contractables.addAll(contractWithContractors);

        String fileName = getContractsFilePath();
        ContractExcelWriter.writeContractsToExcel(contractables, fileName);

        return getExcelFile(fileName);
    }
}
