package edu.drexel.se211.CSVLib;

import java.util.ArrayList;

/**
 * A table of CSV rows.
 */
public class CSVTable {
    // Fields
    private ArrayList<CSVRow> rows;
    private CSVRow headers;
    private int longestRow = 0;

    // Constructors

    /**
     * Creates a new CSVTable with no rows.
     */
    public CSVTable() {
        rows = new ArrayList<>();
    }

    /**
     * Creates a new CSVTable with the specified rows.
     * 
     * @param rows The rows to add to the table.
     */
    public CSVTable(ArrayList<CSVRow> rows) {
        this.rows = rows;
        setLongestRow(findLongestRow());
    }

    /**
     * Creates a new CSVTable with the specified rows and headers.
     * 
     * @param rows The rows to add to the table.
     * @param headers The headers to add to the table.
     */
    public CSVTable(ArrayList<CSVRow> rows, CSVRow headers) {
        this(rows);
        this.headers = headers;
    }

    /**
     * Creates a new CSVTable with the specified rows and headers.
     * 
     * @param rows The rows to add to the table.
     * @param headers The headers to add to the table.
     */
    public CSVTable(ArrayList<CSVRow> rows, ArrayList<String> headers) {
        this(rows, new CSVRow(headers));
    }

    // Methods

    /**
     * Returns the headers.
     * 
     * @return The headers.
     */
    public CSVRow getHeaders() {
        return headers;
    }

    /**
     * Returns the rows of the CSV file.
     * 
     * @return the rows of the CSV file.
     */
    public ArrayList<CSVRow> getRows() {
        return rows;
    }

    /**
     * Returns the number of CSVRows in the CSVTable
     * 
     * @return the number of CSVRows in the CSVTable
     */
    public int getRowSize() {
        return rows.size();
    }
    /**
     * Returns the CSVRow at the given index
     * 
     * @param index the index of the CSVRow to return
     * @return the CSVRow at the given index
     */
    public CSVRow getRow(int index) {
        if (index == -1) {
            index = rows.size() - 1;
        }
        return rows.get(index);
    }

    /**
     * Returns the specified row (not including headers) with headers.
     * 
     * @param index The index of the row (not including headers) to return.
     * @return The specified row (not including headers) with headers.
     */
    public CSVRowWithHeaders getRowWithHeaders(int index) {
        return new CSVRowWithHeaders(getRow(index), headers);
    }

    /**
     * Returns all the rows (not including headers) with headers.
     * 
     * @return All the rows (not including headers) with headers.
     */
    public ArrayList<CSVRowWithHeaders> getRowsWithHeaders() {
        ArrayList<CSVRowWithHeaders> rowsWithHeaders = new ArrayList<>();
        for (CSVRow row : rows) {
            rowsWithHeaders.add(new CSVRowWithHeaders(row, headers));
        }
        return rowsWithHeaders;
    }

    /**
     * Adds the specified row to the end of the list of rows.
     * 
     * @param row The row to add to the end of the list of rows.
     */

    public void addRow(CSVRow row) {
        rows.add(row);
        if (row.size() > getLongestRow()) {
            setLongestRow(row.size());
        }
    }

    /**
     * Sets the specified row to the specified row (not including headers).
     * 
     * @param index The index of the row (not including headers) to set.
     * @param row The row to set the specified row to (not including headers).
     */
    public void setRow(int index, CSVRow row) {
        rows.set(index, row);
        if (row.size() > getLongestRow()) {
            setLongestRow(row.size());
        }
    }
    /**
     * Sets the list of rows to the specified list of rows (not including headers).
     * 
     * @param rows The list of rows to set the list of rows to (not including headers).
     */
    public void setRows(ArrayList<CSVRow> rows) {
        this.rows = rows;
        setLongestRow(findLongestRow());
    }

    /**
     * Removes the specified row (not including headers).
     * 
     * @param index The index of the row (not including headers) to remove.
     */
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

    /** 
     * Removes a row from the CSVTable
     * @param row the row to be removed
     */
    public void removeRow(CSVRow row) {
        removeRow(rows.indexOf(row));
    }

    /**
     * Finds the length of the longest row in the CSVTable
     * @return the length of the longest row
     */
    private int findLongestRow() {
        int longest = 0;
        for (CSVRow row : rows) {
            if (row.size() > longest) {
                longest = row.size();
            }
        }
        return longest;
    }

    /**
     * Gets the length of the longest row in the CSVTable
     * @return the length of the longest row
     */
    public int getLongestRow() {
        return longestRow;
    }

    /**
     * Sets the length of the longest row in the CSVTable
     * @param longestRow the length of the longest row
     */
    public void setLongestRow(int longestRow) {
        this.longestRow = longestRow;
        // Update the other rows
        for (CSVRow row : rows) {
            if (row.size() < longestRow) {
                row.setCells(row.getCells(), longestRow);
            }
        }
    }

    /**
     * This method returns true if the row has headers, and false otherwise.
     */
    public boolean hasHeaders() {
        return headers != null;
    }

    /**
     * This method sets the headers of the CSV file to the given CSVRow object.
     */
    public void setHeaders(CSVRow headers) {
        this.headers = headers;
    }

    /**
     * This method sets the headers of the CSV file to the given ArrayList of Strings.
     */
    public void setHeaders(ArrayList<String> headers) {
        this.headers = new CSVRow(headers);
    }

    /**
     * This method returns the number of headers in the CSV file.
     */
    public int getHeaderSize() {
        return headers.size();
    }

    /**
     * This method returns the index of the given header. If the header is not found, it returns -1.
     */
    private int getHeaderIndex(String header) {
        return headers.getCells().indexOf(header);
    }

    /**
     * This method prints the current CSVTable to the console.
     */
    public void print() {
        if (hasHeaders()) {
            headers.print();
        }
        for (CSVRow row : rows) {
            row.print();
        }
    }
}
