package edu.drexel.se211.CSVLib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final CSVParser parser;
    private final BufferedWriter writer;

    public CSVWriter(String filename) throws IOException {
        this(new CSVParser(), new BufferedWriter(new FileWriter(filename)));
    }
    public CSVWriter(String filename, CSVParser parser) throws IOException {
        this(parser, new BufferedWriter(new FileWriter(filename)));
    }
    public CSVWriter(CSVParser parser, BufferedWriter writer) {
        this.parser = parser;
        this.writer = writer;
    }

    public CSVWriter(File selectedFile) throws IOException {
        this(new CSVParser(), new BufferedWriter(new FileWriter(selectedFile)));
    }

    public void close() throws IOException {
        writer.close();
    }

    public void writeTable(CSVTable table) {
    try {
        CSVRow headers = table.getHeaders();
        if (headers != null) {
            writer.write(headers.toString(parser, table.getLongestRow()));
        }
        for (CSVRow row : table.getRows()) {
            writer.write(row.toString(parser, table.getLongestRow()));
        }
    } catch (IOException e) {
        System.out.println(e.getMessage());
    } finally {
        try {
            close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
}
