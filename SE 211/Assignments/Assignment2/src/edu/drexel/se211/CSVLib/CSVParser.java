package edu.drexel.se211.CSVLib;

import java.util.HashMap;

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
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
    public char getQuote() {
        return quote;
    }
    public void setQuote(char quote) {
        this.quote = quote;
    }
    public char getEscape() {
        return escape;
    }
    public void setEscape(char escape) {
        this.escape = escape;
    }
    public boolean hasHeaders() {
        return hasHeader;
    }
    public void setHasHeaders(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }
    char detectDelimiter(String line) {
        // Count the number of symbols (skip quotes and escapes)
        HashMap<Character, Integer> symbolCount = new HashMap<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (symbolCount.containsKey(c)) {
                symbolCount.put(c, symbolCount.get(c) + 1);
            } else {
                symbolCount.put(c, 1);
            }
        }
        // Find the most common symbol (not letter or number)
        char mostCommonSymbol = 0;
        for (char c : symbolCount.keySet()) {
            if (c == getQuote() || c == getEscape() || Character.isLetterOrDigit(c) || c == ' ') {
                continue;
            }
            if (mostCommonSymbol == 0 || symbolCount.get(c) > symbolCount.get(mostCommonSymbol)) {
                mostCommonSymbol = c;
            }
        }
        return mostCommonSymbol;
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
            } else if (c == getEscape()) {
                inEscape = true;
            } else if (c == getQuote()) {
                inQuote = !inQuote;
            } else if (c == getDelimiter() && !inQuote) {
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
        int start = 0;
        delimiter = detectDelimiter(lines[0]);
        if (hasHeaders()) {
            csvTable.setHeaders(parseLine(lines[0]));
            start = 1;
        }
        for (int i = start; i < lines.length; i++) {
            CSVRow row = parseLine(lines[i]);
            csvTable.addRow(row);
        }
        return csvTable;
    }

    public String toSingleLine(CSVRow row, int longestRow) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < longestRow; i++) {
            String cell = row.getCell(i, "");
            if (cell.contains(Character.toString(getDelimiter())) || cell.contains(Character.toString(getQuote()))) {
                line.append(getQuote());
                for (int j = 0; j < cell.length(); j++) {
                    char c = cell.charAt(j);
                    if (c == getQuote()) {
                        line.append(getEscape());
                    }
                    line.append(c);
                }
                line.append(getQuote());
            } else {
                line.append(cell);
            }
            if (i < row.size() - 1) {
                line.append(getDelimiter());
            }
        }
        line.append("\r\n");
        return line.toString();
    }

    public String toSingleLine(CSVRow row) {
        return toSingleLine(row, row.size());
    }
}
