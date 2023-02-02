package edu.drexel.se211.csvLibrary;

import java.util.ArrayList;

public class CSVTable {
    // Fields
    private ArrayList<CSVRow> rows;
    private CSVRow header;

    // Constructors
    public CSVTable() {
        rows = new ArrayList<>();
    }
    public CSVTable(ArrayList<CSVRow> rows) {
        this.rows = rows;
    }
    public CSVTable(ArrayList<CSVRow> rows, CSVRow header) {
        this(rows);
        this.header = header;
    }

    // Methods
    public CSVRow getHeader() {
        return header;
    }
    public boolean hasHeader() {
        return header != null;
    }
    public void setHeader(CSVRow header) {
        this.header = header;
    }
    public ArrayList<CSVRow> getRows() {
        return rows;
    }
    public int getRowCount() {
        return rows.size();
    }
    public void addRow(CSVRow row) {
        rows.add(row);
    }
    public void setRows(ArrayList<CSVRow> rows) {
        this.rows = rows;
    }
    public CSVRow getRow(int index) {
        return rows.get(index);
    }
    public CSVRowWithHeader getRowWithHeader(int index) {
        return new CSVRowWithHeader(getRow(index), header);
    }
    public void setRow(int index, CSVRow row) {
        rows.set(index, row);
    }
    public void removeRow(int index) {
        rows.remove(index);
    }
    public void removeRow(CSVRow row) {
        rows.remove(row);
    }
    public void clear() {
        rows.clear();
    }
    public void addRows(ArrayList<CSVRow> rows) {
        this.rows.addAll(rows);
    }
    public void addRows(CSVTable table) {
        addRows(table.getRows());
    }
}
