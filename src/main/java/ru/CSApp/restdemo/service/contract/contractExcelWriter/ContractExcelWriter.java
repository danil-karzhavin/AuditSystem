package ru.CSApp.restdemo.service.contract.contractExcelWriter;

import org.apache.poi.ss.usermodel.*;
import org.springframework.scheduling.annotation.Async;
import ru.CSApp.restdemo.model.contract.Contract;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.CSApp.restdemo.model.contract.IContractable;

public class ContractExcelWriter {
    @Async
    public static void writeContractsToExcel(List<IContractable> contractable, String filePath) {
        Workbook workbook = new XSSFWorkbook(); // Создание нового экземпляра рабочей книги Excel
        try {
            Sheet sheet = workbook.createSheet("Contract Details"); // Создаётся лист с именем "Contract Details".
            createHeaderRow(sheet);
            for(int i = 0; i < contractable.size(); i++)
                createDataRow(sheet, contractable.get(i), i + 1);

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
        headerRow.createCell(2).setCellValue("Type");
        headerRow.createCell(3).setCellValue("Plan Start Date");
        headerRow.createCell(4).setCellValue("Plan End Date");
        headerRow.createCell(5).setCellValue("Actual Start Date");
        headerRow.createCell(6).setCellValue("Actual End Date");
        headerRow.createCell(7).setCellValue("Monetary Value");
        headerRow.createCell(8).setCellValue("Main");

        // Настройка стиля (например, жирный шрифт)
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        for (Cell cell : headerRow) {
            cell.setCellStyle(headerStyle);
        }

        // настройка авторазмера ячеек
        for (int i = 0; i < 9; ++i)
            sheet.autoSizeColumn(i);
    }

    private static void createDataRow(Sheet sheet, IContractable contractable, Integer numRow) {
        Row dataRow = sheet.createRow(numRow); // создаем строку, в которую помещаем значения полей
        dataRow.createCell(0).setCellValue(contractable.getId());
        dataRow.createCell(1).setCellValue(contractable.getName());
        dataRow.createCell(2).setCellValue(contractable.getType());

        setDateValue(dataRow.createCell(3), contractable.getPlanStartDate());
        setDateValue(dataRow.createCell(4), contractable.getPlanEndDate());
        setDateValue(dataRow.createCell(5), contractable.getActualStartDate());
        setDateValue(dataRow.createCell(6), contractable.getActualEndDate());

        dataRow.createCell(7).setCellValue(contractable.getMonetaryValue() != null ? contractable.getMonetaryValue() : 0);

        if (contractable instanceof Contract)
            dataRow.createCell(8).setCellValue("Yes");
        else
            dataRow.createCell(8).setCellValue("No");

        // настройка авторазмера ячеек
        for (int i = 0; i < 9; ++i)
            sheet.autoSizeColumn(i);
    }

    private static void setDateValue(Cell cell, LocalDate date) {

        CellStyle cellStyle = cell.getSheet().getWorkbook().createCellStyle();

        CreationHelper creationHelper = cell.getSheet().getWorkbook().getCreationHelper();

        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd"));
        cell.setCellValue(date);
        cell.setCellStyle(cellStyle);
    }
}
