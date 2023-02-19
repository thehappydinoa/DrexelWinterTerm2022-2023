package edu.drexel.se211.CSVLib;

import java.util.ArrayList;

/**
 * A row of a CSV table.
 */
public class CSVRow {
    // Fields
    private ArrayList<String> cells;

    // Constructors

    /**
     * Creates a new CSVRow with no cells.
     */
    public CSVRow() {
        this(new ArrayList<>());
    }

    /**
     * Creates a new CSVRow with the specified number of cells.
     *
     * @param size The number of cells to create.
     */
    public CSVRow(int size) {
        this(new ArrayList<>(size));
        fillCells(size);
    }

    /**
     * Creates a new CSVRow with the specified cells.
     *
     * @param cells The cells to add to the row.
     */
    public CSVRow(ArrayList<String> cells) {
        this.cells = cells;
    }

    // Methods

    /**
     * Returns the cells in the row.
     *
     * @return The cells in the row.
     */
    public ArrayList<String> getCells() {
        return cells;
    }

    /**
     * Sets the cells in the row.
     *
     * @param cells The cells to set.
     */
    public void setCells(ArrayList<String> cells) {
        this.cells = cells;
    }

    /**
     * Sets the cells in the row.
     *
     * @param cells The cells to set.
     * @param size  The number of cells to set.
     */
    public void setCells(ArrayList<String> cells, int size) {
        setCells(cells);
        fillCells(size);
    }

    /**
     * Fill the row with null cells until it has the specified size.
     *
     * @param size      The size to fill the row to.
     * @param nullValue The value to fill the cells with.
     */
    public void fillCells(int size, String nullValue) {
        while (cells.size() < size) {
            cells.add(nullValue);
        }
    }

    /**
     * Fill the row with null cells until it has the specified size.
     *
     * @param size The size to fill the row to.
     */
    public void fillCells(int size) {
        fillCells(size, null);
    }

    /**
     * Returns the number of cells in the row.
     *
     * @return The number of cells in the row.
     */
    public int size() {
        return cells.size();
    }

    /**
     * Adds a cell to the end of the row.
     *
     * @param cell The cell to add.
     */
    public void addCell(String cell) {
        cells.add(cell);
    }

    /**
     * Returns the cell at the specified index.
     *
     * @param index The index of the cell to get.
     * @return The cell at the specified index.
     * @see #getCell(int)
     */
    public String get(int index) {
        return getCell(index);
    }

    /**
     * Returns the cell at the specified index.
     *
     * @param index The index of the cell to get.
     * @return The cell at the specified index.
     */
    public String getCell(int index) {
        return cells.get(index);
    }

    /**
     * Returns the cell at the specified index, or the specified default value if the index is out of bounds.
     *
     * @param index        The index of the cell to get.
     * @param defaultValue The value to return if the index is out of bounds.
     * @return The cell at the specified index, or the specified default value if the index is out of bounds.
     */
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

    /**
     * Sets the cell at the specified index.
     *
     * @param index The index of the cell to set.
     * @param cell  The cell to set.
     */
    public void setCell(int index, String cell) {
        // If the index is out of bounds, add null cells until it's not
        while (index >= cells.size()) {
            cells.add(null);
        }
        cells.set(index, cell);
    }

    /**
     * Removes the cell at the specified index.
     *
     * @param index The index of the cell to remove.
     */
    public void removeCell(int index) {
        cells.remove(index);
    }

    /**
     * Returns the row as a string.
     *
     * @param delimiter The delimiter to use.
     * @param quote     The quote character to use.
     * @param escape    The escape character to use.
     * @param nullValue The value to use for null cells.
     * @param newLine   The new line character to use.
     * @param mostCells The most cells in any row.
     * @return The row as a string.
     */
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

    /**
     * Returns the row as a string.
     *
     * @param delimiter The delimiter to use.
     * @param quote     The quote character to use.
     * @param escape    The escape character to use.
     * @param nullValue The value to use for null cells.
     * @param newLine   The new line character to use.
     * @return The row as a string.
     */
    public String toString(char delimiter, char quote, char escape, String nullValue, String newLine) {
        return toString(delimiter, quote, escape, nullValue, newLine, size());
    }

    /**
     * Returns the row as a string.
     *
     * @param delimiter The delimiter to use.
     * @param quote     The quote character to use.
     * @param escape    The escape character to use.
     * @param nullValue The value to use for null cells.
     * @return The row as a string.
     */
    public String toString(char delimiter, char quote, char escape, String nullValue) {
        return toString(delimiter, quote, escape, nullValue, CSVParser.DEFAULT_NEWLINE);
    }

    /**
     * Returns the row as a string.
     *
     * @param delimiter The delimiter to use.
     * @param quote     The quote character to use.
     * @param escape    The escape character to use.
     * @return The row as a string.
     */
    public String toString(char delimiter, char quote, char escape) {
        return toString(delimiter, quote, escape, CSVParser.DEFAULT_NULL);
    }

    /**
     * Returns the row as a string.
     *
     * @param delimiter The delimiter to use.
     * @param quote     The quote character to use.
     * @return The row as a string.
     */
    public String toString(char delimiter, char quote) {
        return toString(delimiter, quote, CSVParser.DEFAULT_ESCAPE);
    }

    /**
     * Returns the row as a string.
     *
     * @param delimiter The delimiter to use.
     * @return The row as a string.
     */
    public String toString(char delimiter) {
        return toString(delimiter, CSVParser.DEFAULT_QUOTE);
    }

    /**
     * Returns the row as a string.
     *
     * @return The row as a string.
     */
    public String toString() {
        return toString(CSVParser.DEFAULT_DELIMITER);
    }

    /**
     * Returns the row as a string.
     *
     * @param mostCells The most cells in any row.
     * @return The row as a string.
     */
    public String toString(int mostCells) {
        return toString(CSVParser.DEFAULT_DELIMITER, CSVParser.DEFAULT_QUOTE, CSVParser.DEFAULT_ESCAPE, CSVParser.DEFAULT_NULL, CSVParser.DEFAULT_NEWLINE, mostCells);
    }

    /**
     * Returns the row as a string.
     *
     * @param parser The parser to use.
     * @return The row as a string.
     */
    public String toString(CSVParser parser) {
        return toString(parser.getDelimiter(), parser.getQuote(), parser.getEscape());
    }

    /**
     * Returns the row as a string.
     *
     * @param parser    The parser to use.
     * @param mostCells The most cells in any row.
     * @return The row as a string.
     */
    public String toString(CSVParser parser, int mostCells) {
        return toString(parser.getDelimiter(), parser.getQuote(), parser.getEscape(), parser.getNullValue(), parser.getNewline(), mostCells);
    }

    /**
     * Prints the row to the console.
     *
     * @param bold Whether to print the row in bold.
     */
    public void print(boolean bold) {
        String s = toString();
        if (bold) {
            System.out.println("\033[1m" + s + "\033[0m");
        } else {
            System.out.println(s);
        }
    }

    /**
     * Prints the row to the console.
     */
    public void print() {
        print(false);
    }
}
