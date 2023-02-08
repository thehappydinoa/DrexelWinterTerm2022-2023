package edu.drexel.se211.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    // Fields
    private CSVParser parser;
    private BufferedReader reader;
    private boolean hasHeader;

    // Constructors
    public CSVReader(String filename) throws FileNotFoundException {
        this(new CSVParser(), new BufferedReader(new FileReader(filename)));
    }
    public CSVReader(String filename, CSVParser parser) throws FileNotFoundException {
        this(parser, new BufferedReader(new FileReader(filename)));
    }
    public CSVReader(CSVParser parser, BufferedReader reader) {
        this(parser, reader, false);
    }
    public CSVReader(CSVParser parser, BufferedReader reader, boolean hasHeader) {
        this.parser = parser;
        this.reader = reader;
        this.hasHeader = hasHeader;
    }

    // Methods
    public CSVParser getParser() {
        return parser;
    }
    public void setParser(CSVParser parser) {
        this.parser = parser;
    }
    public BufferedReader getReader() {
        return reader;
    }
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
    public CSVTable readTable() throws Exception {
        StringBuilder fileContents = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            fileContents.append(line).append("\n");
            line = reader.readLine();
        }
        return parser.parseTable(fileContents.toString());
    }
    public void close() throws IOException {
        reader.close();
    }
}
