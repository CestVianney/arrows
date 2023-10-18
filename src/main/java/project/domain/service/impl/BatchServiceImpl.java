package project.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.domain.enums.ExtensionFichierEnum;
import project.domain.model.InputStreamElement;
import project.domain.service.ArrowsService;
import project.domain.service.BatchService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final ArrowsService arrowsService;
    private final ResourceLoader resourceLoader;

    @Override
    @Scheduled(fixedRate = 60000)
    public void triggerTxtToCsvBatch() {
        Resource resourceBatchTxt = resourceLoader.getResource("classpath:batch/input");

        try {
            if (resourceBatchTxt.isFile()) {
                File folder = resourceBatchTxt.getFile();
                if (folder.exists() && folder.isDirectory()) {
                    File[] files = folder.listFiles();

                    if (files != null) {
                        for (File file : files) {
                            System.out.println("Traitement du fichier : " + file.getName());
                            createCsvFile(file);
                            moveFromTxtToDone(resourceBatchTxt, file);
                        }
                    } else {
                        System.out.println("Le dossier est vide.");
                    }
                } else {
                    System.err.println("Le dossier spécifié n'existe pas ou n'est pas un dossier valide.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveFromTxtToDone(Resource resourceBatchTxt, File file) throws IOException {
        Resource resourceBatchDone = resourceLoader.getResource("classpath:batch/output/done");
        Path txtPath = Paths.get(resourceBatchTxt.getURI());
        Path donePath = Paths.get(resourceBatchDone.getURI());
        Files.move(txtPath.resolve(file.getName()), donePath.resolve(file.getName()));
    }

    private void createCsvFile(File file) throws IOException {
        byte[] outputStream = processAndTransformTxtToCsv(file);

        Resource resourceBatchCsv = resourceLoader.getResource("classpath:batch/output/csv");
        File directory = resourceBatchCsv.getFile();
        Path filePath = directory.toPath().resolve(file.getName().substring(0, file.getName().indexOf(".")) + ExtensionFichierEnum.CSV.getExtension());
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            Files.write(filePath, outputStream, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            System.out.println("Le fichier du nom de " + filePath.toString() + " existe déjà dans resource/batch/csv !");
        }
    }

    private byte[] processAndTransformTxtToCsv(File file) throws IOException {
        List<String> csvData = new ArrayList<>();
        try (InputStream is = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = processLine(line);
                csvData.add(processedLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(outputStream),
                CSVFormat.DEFAULT)) {
            for (String csvLine : csvData) {
                csvPrinter.printRecord(csvLine.split(","));
            }
        }

        return outputStream.toByteArray();
    }

    private String processLine(String line) {
        InputStreamElement inputStreamElement = arrowsService.countFinalScore(line);
        return inputStreamElement.getInput() + "," +
                (int) inputStreamElement.getScore();
    }
}
