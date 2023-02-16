package edu.drexel.se211.CSVLib;

import java.util.ArrayList;

public class CSVRowWithHeaders extends CSVRow {
    // Fields
    private CSVRow headers;

    // Constructors
    public CSVRowWithHeaders() {
        super();
        headers = new CSVRow();
    }
    public CSVRowWithHeaders(CSVRow row, CSVRow headers) {
        super(row.getCells());
        this.headers = headers;
    }
    public CSVRowWithHeaders(ArrayList<String> cells, CSVRow headers) {
        super(cells);
        this.headers = headers;
    }
    public CSVRowWithHeaders(ArrayList<String> cells, ArrayList<String> headers) {
        super(cells);
        this.headers = new CSVRow(headers);
    }

    // Methods
    public CSVRow getHeaders() {
        return headers;
    }
    public void setHeaders(CSVRow headers) {
        this.headers = headers;
    }
    public void setHeaders(ArrayList<String> headers) {
        this.headers.setCells(headers);
    }
    public String getHeader(int index) {
        return headers.getCell(index);
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
    public int getHeaderCount() {
        return headers.size();
    }

    @Override
    public void print() {
        print(true);
    }
}
