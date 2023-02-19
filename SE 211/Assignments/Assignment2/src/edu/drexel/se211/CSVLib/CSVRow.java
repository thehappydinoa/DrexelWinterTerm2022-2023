package edu.drexel.se211.CSVLib;

import java.util.ArrayList;

public class CSVRow {
    // Fields
    private ArrayList<String> cells;

    // Constructors
    public CSVRow() {
        this(new ArrayList<>());
    }
    public CSVRow(int size) {
        this(new ArrayList<>(size));
        // Fill with nulls
        for (int i = 0; i < size; i++) {
            cells.add(null);
        }
    }
    public CSVRow(ArrayList<String> cells) {
        this.cells = cells;
    }

    // Methods
    public ArrayList<String> getCells() {
        return cells;
    }
    public int size() {
        return cells.size();
    }
    public void addCell(String cell) {
        cells.add(cell);
    }
    public void setCells(ArrayList<String> cells) {
        this.cells = cells;
    }
    public void setCells(ArrayList<String> cells, int size) {
        this.cells = cells;
        while (this.cells.size() < size) {
            this.cells.add(null);
        }
    }
    public String get(int index) {
        return getCell(index);
    }
    public String getCell(int index) {
        return cells.get(index);
    }
    public String getCell(int index, String defaultValue) {
        if (index >= cells.size()) {
            return defaultValue;
        }
        try {
            return cells.get(index);
        } catch (IndexOutOfBoundsException e) {
            return defaultValue;
        }
    }
    public void setCell(int index, String cell) {
        // If the index is out of bounds, add null cells until it's not
        while (index >= cells.size()) {
            cells.add(null);
        }
        cells.set(index, cell);
    }
    public void removeCell(int index) {
        cells.remove(index);
    }
    public String toString(char delimiter, char quote, char escape, String nullValue, String newLine, int mostCells) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cells.size(); i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            String cell = cells.get(i);
            if (cell != null) {
                boolean needsQuotes = cell.indexOf(delimiter) >= 0 || cell.indexOf(quote) >= 0 || cell.indexOf(escape) >= 0;
                if (needsQuotes) {
                    sb.append(quote);
                    for (int j = 0; j < cell.length(); j++) {
                        char c = cell.charAt(j);
                        if (c == quote || c == escape) {
                            sb.append(escape);
                        }
                        sb.append(c);
                    }
                    sb.append(quote);
                } else {
                    sb.append(cell);
                }
            }
        }

        sb.append((nullValue + delimiter).repeat(Math.max(0, mostCells - cells.size())));
        sb.append(newLine);
        return sb.toString();
    }


    public String toString(int mostCells) {
        return toString(CSVParser.DEFAULT_DELIMITER, CSVParser.DEFAULT_QUOTE, CSVParser.DEFAULT_ESCAPE, CSVParser.DEFAULT_NULL, CSVParser.DEFAULT_NEWLINE, mostCells);
    }
    public String toString(char delimiter, char quote, char escape) {
        return toString(delimiter, quote, escape, CSVParser.DEFAULT_NULL, CSVParser.DEFAULT_NEWLINE, size());
    }

    public String toString(CSVParser parser) {
        return toString(parser.getDelimiter(), parser.getQuote(), parser.getEscape());
    }

    public String toString(CSVParser parser, int mostCells) {
        return toString(parser.getDelimiter(), parser.getQuote(), parser.getEscape(), parser.getNullValue(), parser.getNewline(), mostCells);
    }

    public String toString() {
        return toString(CSVParser.DEFAULT_DELIMITER, CSVParser.DEFAULT_QUOTE, CSVParser.DEFAULT_ESCAPE, CSVParser.DEFAULT_NULL, CSVParser.DEFAULT_NEWLINE, size());
    }

    public void print(boolean bold) {
        String s = toString();
        if (bold) {
            System.out.println("\033[1m" + s + "\033[0m");
        } else {
            System.out.println(s);
        }
    }

    public void print() {
        print(false);
    }
}
