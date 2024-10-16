package ru.CSApp.restdemo.service.contract.contractExcelWriter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.CSApp.restdemo.model.ContractStage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ContractStagesExcelWriter {
    public static void writeContractStagesToExcel(List<ContractStage> contractStages, String filePath) {
        Workbook workbook = new XSSFWorkbook(); // Создание нового экземпляра рабочей книги Excel
        try {
            Sheet sheet = workbook.createSheet("Contract Stages Details"); // Создаётся лист с именем "Contract Details".
            createHeaderRow(sheet);
            for(int i = 0; i < contractStages.size(); i++){
                ContractStage contractStage = contractStages.get(i);
                if (contractStage == null)
                    continue;

                createDataRow(sheet, contractStage, i + 1);
            }

            // Записываем данные в файл
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0); // создаем строку и записываем значения полей
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Plan Start Date");
        headerRow.createCell(3).setCellValue("Plan End Date");
        headerRow.createCell(4).setCellValue("Actual Start Date");
        headerRow.createCell(5).setCellValue("Actual End Date");
        headerRow.createCell(6).setCellValue("Monetary Value");

        // Настройка стиля (например, жирный шрифт)
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        for (Cell cell : headerRow) {
            cell.setCellStyle(headerStyle);
        }
    }

    private static void createDataRow(Sheet sheet, ContractStage contractStage, Integer numRow) {
        Row dataRow = sheet.createRow(numRow); // создаем строку, в которую помещаем значения полей
        dataRow.createCell(0).setCellValue(contractStage.getId());
        dataRow.createCell(1).setCellValue(contractStage.getName());

        setDateValue(dataRow.createCell(2), contractStage.getPlanStartDate());
        setDateValue(dataRow.createCell(3), contractStage.getPlanEndDate());
        setDateValue(dataRow.createCell(4), contractStage.getActualStartDate());
        setDateValue(dataRow.createCell(5), contractStage.getActualEndDate());

        dataRow.createCell(6).setCellValue(contractStage.getMonetaryValue() != null ? contractStage.getMonetaryValue() : 0);
    }

    private static void setDateValue(Cell cell, LocalDate date) {

        CellStyle cellStyle = cell.getSheet().getWorkbook().createCellStyle();

        CreationHelper creationHelper = cell.getSheet().getWorkbook().getCreationHelper();

        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd"));
        cell.setCellValue(date);
        cell.setCellStyle(cellStyle);
    }
}