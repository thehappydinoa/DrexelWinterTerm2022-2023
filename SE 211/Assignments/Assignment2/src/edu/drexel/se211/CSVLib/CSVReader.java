package edu.drexel.se211.CSVLib;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A CSVReader reads a CSV file and returns a CSVTable.
 */
public class CSVReader {
    // Fields
    private CSVParser parser;
    private BufferedReader reader;

    // Constructors

    /**
     * Creates a new CSVReader.
     *
     * @param filename the name of the file to read from
     * @throws FileNotFoundException if the file does not exist
     */
    public CSVReader(String filename) throws FileNotFoundException {
        this(new CSVParser(), new BufferedReader(new FileReader(filename)));
    }

    /**
     * Creates a new CSVReader.
     *
     * @param filename the name of the file to read from
     * @param parser   the parser to use
     * @throws FileNotFoundException if the file does not exist
     */
    public CSVReader(String filename, CSVParser parser) throws FileNotFoundException {
        this(parser, new BufferedReader(new FileReader(filename)));
    }

    /**
     * Creates a new CSVReader.
     *
     * @param parser the parser to use
     * @param reader the reader to use
     */
    public CSVReader(CSVParser parser, BufferedReader reader) {
        this(parser, reader, false);
    }

    /**
     * Creates a new CSVReader.
     *
     * @param parser    the parser to use
     * @param reader    the reader to use
     * @param hasHeader whether the table read from the underlying reader has a header
     */
    public CSVReader(CSVParser parser, BufferedReader reader, boolean hasHeader) {
        this.parser = parser;
        this.reader = reader;
        setHasHeader(hasHeader);
    }

    // Methods

    /**
     * Returns the parser.
     *
     * @return the parser
     */
    public CSVParser getParser() {
        return parser;
    }

    /**
     * Sets the parser.
     *
     * @param parser the parser
     */
    public void setParser(CSVParser parser) {
        this.parser = parser;
    }

    /**
     * Returns the underlying reader.
     *
     * @return the underlying reader
     */
    public BufferedReader getReader() {
        return reader;
    }

    /**
     * Sets the underlying reader.
     *
     * @param reader the underlying reader
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Returns whether the table read from the underlying reader has a header.
     *
     * @return whether the table read from the underlying reader has a header
     */
    public boolean hasHeader() {
        return parser.hasHeaders();
    }

    /**
     * Sets whether the table read from the underlying reader has a header.
     *
     * @param hasHeader whether the table read from the underlying reader has a header
     */
    public void setHasHeader(boolean hasHeader) {
        parser.setHasHeaders(hasHeader);
    }

    /**
     * Reads a table from the underlying reader.
     *
     * @return the table read from the underlying reader
     * @throws Exception if an error occurs while reading the table
     */
    public CSVTable readTable() throws Exception {
        StringBuilder fileContents = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            fileContents.append(line).append("\n");
            line = reader.readLine();
        }
        return parser.parseTable(fileContents.toString());
    }

    /**
     * Closes the underlying reader.
     *
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException {
        reader.close();
    }
}
