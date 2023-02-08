package edu.drexel.se211.csv;

public class CSVParser {
    // Constants
    public static final char DEFAULT_DELIMITER = ',';
    public static final char DEFAULT_QUOTE = '"';
    public static final char DEFAULT_ESCAPE = '\\';
    public static final boolean DEFAULT_HAS_HEADER = false;

    // Fields
    private char delimiter;
    private char quote;
    private char escape;
    private boolean hasHeader;

    // Constructors
    public CSVParser() {
        this(DEFAULT_DELIMITER);
    }
    public CSVParser(char delimiter) {
        this(delimiter, DEFAULT_QUOTE);
    }
    public CSVParser(char delimiter, char quote) {
        this(delimiter, quote, DEFAULT_ESCAPE);
    }
    public CSVParser(char delimiter, char quote, char escape) {
        this(delimiter, quote, escape, DEFAULT_HAS_HEADER);
    }
    public CSVParser(char delimiter, char quote, char escape, boolean hasHeader) {
        this.delimiter = delimiter;
        this.quote = quote;
        this.escape = escape;
        this.hasHeader = hasHeader;
    }

    // Methods
    public char getDelimiter() {
        return delimiter;
    }
    public char getQuote() {
        return quote;
    }
    public char getEscape() {
        return escape;
    }
    public boolean hasHeader() {
        return hasHeader;
    }
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
    public void setQuote(char quote) {
        this.quote = quote;
    }
    public void setEscape(char escape) {
        this.escape = escape;
    }
    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }
    private char automaticDelimiter(String line) {
        int commaCount = 0;
        int semicolonCount = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ',') {
                commaCount++;
            } else if (c == ';') {
                semicolonCount++;
            }
        }
        if (commaCount > semicolonCount) {
            return ',';
        } else {
            return ';';
        }
    }
    public CSVRow parseLine(String line) {
        CSVRow row = new CSVRow();
        StringBuilder cell = new StringBuilder();
        boolean inQuote = false;
        boolean inEscape = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inEscape) {
                cell.append(c);
                inEscape = false;
            } else if (c == escape) {
                inEscape = true;
            } else if (c == quote) {
                inQuote = !inQuote;
            } else if (c == delimiter && !inQuote) {
                row.addCell(cell.toString());
                cell = new StringBuilder();
            } else {
                cell.append(c);
            }
        }
        row.addCell(cell.toString());
        return row;
    }
    public CSVTable parseTable(String table) {
        CSVTable csvTable = new CSVTable();
        String[] lines = table.split("\r\n|\r|\n");
        delimiter = automaticDelimiter(lines[0]);
        if (hasHeader()) {
            CSVRow header = parseLine(lines[0]);
            csvTable.setHeader(header);
            for (int i = 1; i < lines.length; i++) {
                CSVRow row = parseLine(lines[i]);
                csvTable.addRow(row);
            }
        } else {
            for (int i = 0; i < lines.length; i++) {
                CSVRow row = parseLine(lines[i]);
                csvTable.addRow(row);
            }
        }
        return csvTable;
    }
}
