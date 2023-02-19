package edu.drexel.se211.CSVLib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A writer for CSV tables.
 */
public class CSVWriter {
    private final CSVParser parser;
    private final BufferedWriter writer;

    // Constructors

    /**
     * Creates a new CSVWriter with the specified writer and parser.
     *
     * @param parser The parser to use.
     * @param writer The writer to use.
     */
    public CSVWriter(CSVParser parser, BufferedWriter writer) {
        this.parser = parser;
        this.writer = writer;
    }

    /**
     * Creates a new CSVWriter with the specified writer and the default parser.
     *
     * @param writer The writer to use.
     * @throws IOException If the file cannot be opened.
     */
    public CSVWriter(BufferedWriter writer) throws IOException {
        this(new CSVParser(), writer);
    }

    /**
     * Creates a new CSVWriter with the specified filename and the default parser.
     *
     * @param filename The filename to write to.
     * @throws IOException If the file cannot be opened.
     */
    public CSVWriter(String filename) throws IOException {
        this(new BufferedWriter(new FileWriter(filename)));
    }

    /**
     * Creates a new CSVWriter with the specified file and the default parser.
     *
     * @param selectedFile The file to write to.
     * @throws IOException If the file cannot be opened.
     */
    public CSVWriter(File selectedFile) throws IOException {
        this(new BufferedWriter(new FileWriter(selectedFile)));
    }

    /**
     * Creates a new CSVWriter with the specified filename and parser.
     *
     * @param parser   The parser to use.
     * @param filename The filename to write to.
     * @throws IOException If the file cannot be opened.
     */
    public CSVWriter(CSVParser parser, String filename) throws IOException {
        this(parser, new BufferedWriter(new FileWriter(filename)));
    }

    // Methods

    /**
     * Closes the writer.
     *
     * @throws IOException If the writer cannot be closed.
     */
    public void close() throws IOException {
        writer.close();
    }

    /**
     * Writes the specified table to the writer.
     *
     * @param table The table to write.
     */
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
