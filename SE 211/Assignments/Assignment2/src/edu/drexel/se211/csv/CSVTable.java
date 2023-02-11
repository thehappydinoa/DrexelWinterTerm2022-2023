package edu.drexel.se211.csv;

import java.util.ArrayList;

public class CSVTable {
    // Fields
    private ArrayList<CSVRow> rows;
    private CSVRow headers;

    // Constructors
    public CSVTable() {
        rows = new ArrayList<>();
    }
    public CSVTable(ArrayList<CSVRow> rows) {
        this.rows = rows;
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
    public int getRowCount() {
        return rows.size();
    }
    public CSVRow getRow(int index) {
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
    }
    public void setRow(int index, CSVRow row) {
        rows.set(index, row);
    }
    public void setRows(ArrayList<CSVRow> rows) {
        this.rows = rows;
    }
    public void removeRow(int index) {
        rows.remove(index);
    }
    public void removeRow(CSVRow row) {
        rows.remove(row);
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
    public int getHeaderCount() {
        return headers.size();
    }
    private int getHeaderIndex(String header) {
        return headers.getCells().indexOf(header);
    }
    private int getHeaderIndex(String header, boolean ignoreCase) {
        if (ignoreCase) {
            for (int i = 0; i < headers.size(); i++) {
                if (headers.getCell(i).equalsIgnoreCase(header)) {
                    return i;
                }
            }
            return -1;
        } else {
            return getHeaderIndex(header);
        }
    }

    public void print() {
        if (hasHeaders()) {
            headers.print();
        }
        for (CSVRow row : rows) {
            row.print();
        }
    }
}
