package edu.drexel.se211.CSVLib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private CSVParser parser;
    private BufferedWriter writer;

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

    public void writeTable(CSVTable table) {
        try {
            if (table.getHeaders() != null) {
                writer.write(parser.toSingleLine(table.getHeaders(), table.getLongestRow()));
            }
            for (CSVRow row : table.getRows()) {
                writer.write(parser.toSingleLine(row, table.getLongestRow()));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}