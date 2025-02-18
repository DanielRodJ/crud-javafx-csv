package com.github.danielrodj.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private final Path csvFilePath;
    private final String header;

    public FileHandler(String csvFilePath, String header) {
        this.csvFilePath = Path.of(csvFilePath);
        this.header = header;
    }

    public List<String[]> readAll() throws IOException {
        List<String[]> lines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(csvFilePath)) {
            String line;
            boolean headersSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headersSkipped) {
                    headersSkipped = true;
                    continue;
                }
                lines.add(line.split(";"));
            }
        }
        return lines;
    }

    public void writeAll(List<String> lines) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(csvFilePath)) {
            bw.write(header);
            bw.newLine();
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public void appendLine(String line) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(csvFilePath, StandardOpenOption.APPEND)) {
            bw.write(line);
            bw.newLine();
        }
    }
}