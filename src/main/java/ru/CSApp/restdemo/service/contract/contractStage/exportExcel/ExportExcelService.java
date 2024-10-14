package ru.CSApp.restdemo.service.contract.contractStage.exportExcel;

import org.springframework.stereotype.Service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ExportExcelService {
    private final String fileDirectory = "C:/Users/karzh/source/repos/Java/CSApp/excelFiles";
    private final Path fileStorageLocation = Paths.get("/path/to/your/files").toAbsolutePath().normalize();

    public Resource getFile(String fileName) throws IOException {
        File file = new File(fileDirectory + "/" + fileName);
        if (!file.exists()) {
            throw new IOException("File not found");
        }
        return Files.readAllBytes(file.toPath());
    }
}
