package edu.drexel.se211.CSVLib;

import java.util.ArrayList;

/**
 * A row of a CSV table.
 */
public class CSVRowWithHeaders extends CSVRow {
    // Fields
    private CSVRow headers;

    // Constructors

    /**
     * Creates a new CSVRowWithHeaders with no cells.
     */
    public CSVRowWithHeaders() {
        super();
        headers = new CSVRow();
    }

    /**
     * Creates a new CSVRowWithHeaders with the specified cells.
     *
     * @param row     The row to add to the table.
     * @param headers The headers to add to the table.
     */
    public CSVRowWithHeaders(CSVRow row, CSVRow headers) {
        super(row.getCells());
        this.headers = headers;
    }

    /**
     * Creates a new CSVRowWithHeaders with the specified cells.
     *
     * @param cells   The cells to add to the row.
     * @param headers The headers to add to the table.
     */
    public CSVRowWithHeaders(ArrayList<String> cells, CSVRow headers) {
        super(cells);
        this.headers = headers;
    }

    /**
     * Creates a new CSVRowWithHeaders with the specified cells.
     *
     * @param cells The cells to add to the row.
     */
    public CSVRowWithHeaders(ArrayList<String> cells, ArrayList<String> headers) {
        super(cells);
        this.headers = new CSVRow(headers);
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
     * Sets the headers.
     *
     * @param headers The headers.
     */
    public void setHeaders(CSVRow headers) {
        this.headers = headers;
    }

    /**
     * Sets the headers.
     *
     * @param headers The headers.
     */
    public void setHeaders(ArrayList<String> headers) {
        this.headers.setCells(headers);
    }

    /**
     * Returns the header at the specified index.
     *
     * @param index The index of the header to return.
     * @return The header at the specified index.
     */
    public String getHeader(int index) {
        return headers.getCell(index);
    }

    /**
     * Returns the header index of the specified header.
     *
     * @param header The header to search for.
     * @return The header at the specified index.
     */
    private int getHeaderIndex(String header) {
        return headers.getCells().indexOf(header);
    }

    /**
     * Returns the header at the specified index.
     *
     * @param header     The header to search for.
     * @param ignoreCase Whether to ignore case when searching for the header.
     * @return The header at the specified index.
     */
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

    /**
     * Returns the number of headers.
     *
     * @return The number of headers.
     */
    public int getHeaderCount() {
        return headers.size();
    }

    /**
     * Prints the row to the console.
     */
    @Override
    public void print() {
        print(true);
    }
}
