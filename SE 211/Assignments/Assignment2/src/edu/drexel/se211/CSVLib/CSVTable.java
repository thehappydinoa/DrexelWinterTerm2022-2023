package edu.drexel.se211.CSVLib;

import java.util.ArrayList;

public class CSVTable {
    // Fields
    private ArrayList<CSVRow> rows;
    private CSVRow headers;
    private int longestRow = 0;

    // Constructors
    public CSVTable() {
        rows = new ArrayList<>();
    }
    public CSVTable(ArrayList<CSVRow> rows) {
        this.rows = rows;
        setLongestRow(findLongestRow());
    }
    public CSVTable(ArrayList<CSVRow> rows, CSVRow headers) {
        this(rows);
        this.headers = headers;
    }
    public CSVTable(ArrayList<CSVRow> rows, ArrayList<String> headers) {
        this(rows, new CSVRow(headers));
    }

    // Methods
    public CSVRow getHeaders() {
        return headers;
    }
    public ArrayList<CSVRow> getRows() {
        return rows;
    }
    public int getRowSize() {
        return rows.size();
    }
    public CSVRow getRow(int index) {
        if (index == -1) {
            index = rows.size() - 1;
        }
        return rows.get(index);
    }
    public CSVRowWithHeaders getRowWithHeaders(int index) {
        return new CSVRowWithHeaders(getRow(index), headers);
    }
    public ArrayList<CSVRowWithHeaders> getRowsWithHeaders() {
        ArrayList<CSVRowWithHeaders> rowsWithHeaders = new ArrayList<>();
        for (CSVRow row : rows) {
            rowsWithHeaders.add(new CSVRowWithHeaders(row, headers));
        }
        return rowsWithHeaders;
    }
    public void addRow(CSVRow row) {
        rows.add(row);
        if (row.size() > getLongestRow()) {
            setLongestRow(row.size());
        }
    }
    public void setRow(int index, CSVRow row) {
        rows.set(index, row);
        if (row.size() > getLongestRow()) {
            setLongestRow(row.size());
        }
    }
    public void setRows(ArrayList<CSVRow> rows) {
        this.rows = rows;
        setLongestRow(findLongestRow());
    }
    public void removeRow(int index) {
        // Get the row to be removed
        CSVRow row = getRow(index);
        // If the row is the longest row, find the new longest row
        boolean isLongestRow = row.size() == getLongestRow();
        // Remove the row
        rows.remove(index);
        // If the row was the longest row, find the new longest row
        if (isLongestRow) {
            setLongestRow(findLongestRow());
        }
    }
    public void removeRow(CSVRow row) {
        removeRow(rows.indexOf(row));
    }
    private int findLongestRow() {
        int longest = 0;
        for (CSVRow row : rows) {
            if (row.size() > longest) {
                longest = row.size();
            }
        }
        return longest;
    }
    public int getLongestRow() {
        return longestRow;
    }
    public void setLongestRow(int longestRow) {
        this.longestRow = longestRow;
        // Update the other rows
        for (CSVRow row : rows) {
            if (row.size() < longestRow) {
                row.setCells(row.getCells(), longestRow);
            }
        }
    }
    public boolean hasHeaders() {
        return headers != null;
    }
    public void setHeaders(CSVRow headers) {
        this.headers = headers;
    }
    public void setHeaders(ArrayList<String> headers) {
        this.headers = new CSVRow(headers);
    }
    public int getHeaderSize() {
        return headers.size();
    }
    private int getHeaderIndex(String header) {
        return headers.getCells().indexOf(header);
    }

    public void print() {
        if (hasHeaders()) {
            headers.print();
        }
        for (CSVRow row : rows) {
            row.print();
        }
    }

    public String[][] to2DArray() {
        String[][] data = new String[getRowSize()][getLongestRow()];
        for (int i = 0; i < getRowSize(); i++) {
            CSVRow row = getRow(i);
            for (int j = 0; j < getLongestRow(); j++) {
                data[i][j] = row.getCell(j, "");
            }
        }
        return data;
    }
}
