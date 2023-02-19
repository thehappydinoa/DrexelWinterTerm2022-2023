package edu.drexel.se211.CSVLib;

import java.util.HashMap;

/**
 * A parser for CSV files.
 */
public class CSVParser {
    // Constants
    public static final char DEFAULT_DELIMITER = ',';
    public static final char DEFAULT_QUOTE = '"';
    public static final char DEFAULT_ESCAPE = '\\';
    public static final String DEFAULT_NULL = "";
    public static final String DEFAULT_NEWLINE = "\r\n";
    public static final boolean DEFAULT_HAS_HEADER = false;
    public static final boolean DEFAULT_SHOULD_DETECT_DELIMITER = true;

    // Fields
    private char delimiter;
    private char quote;
    private char escape;
    private String nullValue;
    private String newline;
    private boolean hasHeader;
    private boolean shouldDetectDelimiter;

    // Constructors

    /**
     * Creates a new CSVParser with the default delimiter, quote, escape, and
     * hasHeader values.
     */
    public CSVParser() {
        this(DEFAULT_DELIMITER);
    }

    /**
     * Creates a new CSVParser with the given delimiter, the default quote,
     * escape, and hasHeader values.
     *
     * @param delimiter the delimiter to use
     */
    public CSVParser(char delimiter) {
        this(delimiter, DEFAULT_QUOTE);
    }

    /**
     * Creates a new CSVParser with the given delimiter and quote, the default
     * escape and hasHeader values.
     *
     * @param delimiter the delimiter to use
     * @param quote the quote to use
     */
    public CSVParser(char delimiter, char quote) {
        this(delimiter, quote, DEFAULT_ESCAPE);
    }

    /**
     * Creates a new CSVParser with the given delimiter, quote, and escape, the
     * default hasHeader value.
     *
     * @param delimiter the delimiter to use
     * @param quote the quote to use
     * @param escape the escape to use
     */
    public CSVParser(char delimiter, char quote, char escape) {
        this(delimiter, quote, escape, DEFAULT_NULL);
    }

    /**
     * Creates a new CSVParser with the given delimiter, quote, escape, and
     * hasHeader values.
     *
     * @param delimiter the delimiter to use
     * @param quote the quote to use
     * @param escape the escape to use
     * @param nullValue the value to use for null values
     */
    public CSVParser(char delimiter, char quote, char escape, String nullValue) {
        this(delimiter, quote, escape, nullValue, DEFAULT_NEWLINE);
    }

    /**
     * Creates a new CSVParser with the given delimiter, quote, escape, and
     * hasHeader values.
     *
     * @param delimiter the delimiter to use
     * @param quote the quote to use
     * @param escape the escape to use
     * @param nullValue the value to use for null values
     * @param newline the newline to use
     */
    public CSVParser(char delimiter, char quote, char escape, String nullValue, String newline) {
        this(delimiter, quote, escape, nullValue, newline, DEFAULT_HAS_HEADER);
    }

    /**
     * Creates a new CSVParser with the given delimiter, quote, escape, and
     * hasHeader values.
     *
     * @param delimiter the delimiter to use
     * @param quote the quote to use
     * @param escape the escape to use
     * @param nullValue the value to use for null values
     * @param newline the newline to use
     * @param hasHeader whether the table read from the underlying reader has a
     * header
     */
    public CSVParser(char delimiter, char quote, char escape, String nullValue, String newline, boolean hasHeader) {
        this(delimiter, quote, escape, nullValue, newline, hasHeader, DEFAULT_SHOULD_DETECT_DELIMITER);
    }

    /**
     * Creates a new CSVParser with the given delimiter, quote, escape, and
     * hasHeader values.
     *
     * @param delimiter the delimiter to use
     * @param quote the quote to use
     * @param escape the escape to use
     * @param nullValue the value to use for null values
     * @param newline the newline to use
     * @param hasHeader whether the table read from the underlying reader has a
     * header
     * @param shouldDetectDelimiter whether the parser should detect the delimiter
     * automatically
     */
    public CSVParser(char delimiter, char quote, char escape, String nullValue, String newline, boolean hasHeader, boolean shouldDetectDelimiter) {
        this.delimiter = delimiter;
        this.quote = quote;
        this.escape = escape;
        this.nullValue = nullValue;
        this.newline = newline;
        this.hasHeader = hasHeader;
        this.shouldDetectDelimiter = shouldDetectDelimiter;
    }

    // Methods

    /**
     * Returns the delimiter used by this parser.
     *
     * @return the delimiter used by this parser
     */
    public char getDelimiter() {
        return delimiter;
    }

    /**
     * Sets the delimiter used by this parser.
     *
     * @param delimiter the delimiter to use
     */
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Returns the quote used by this parser.
     *
     * @return the quote used by this parser
     */
    public char getQuote() {
        return quote;
    }

    /**
     * Sets the quote used by this parser.
     *
     * @param quote the quote to use
     */
    public void setQuote(char quote) {
        this.quote = quote;
    }

    /**
     * Returns the escape used by this parser.
     *
     * @return the escape used by this parser
     */
    public char getEscape() {
        return escape;
    }

    /**
     * Sets the escape used by this parser.
     *
     * @param escape the escape to use
     */
    public void setEscape(char escape) {
        this.escape = escape;
    }

    /**
     * Returns the null value used by this parser.
     *
     * @return the null value used by this parser
     */
    public String getNullValue() {
        return nullValue;
    }

    /**
     * Sets the null value used by this parser.
     *
     * @param nullValue the null value to use
     */
    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    /**
     * Returns the newline used by this parser.
     *
     * @return the newline used by this parser
     */
    public String getNewline() {
        return newline;
    }

    /**
     * Sets the newline used by this parser.
     *
     * @param newline the newline to use
     */
    public void setNewline(String newline) {
        this.newline = newline;
    }

    /**
     * Returns whether the table read from the underlying reader has a header.
     *
     * @return whether the table read from the underlying reader has a header
     */
    public boolean hasHeaders() {
        return hasHeader;
    }

    /**
     * Sets whether the table read from the underlying reader has a header.
     *
     * @param hasHeader whether the table read from the underlying reader has a
     * header
     */
    public void setHasHeaders(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    /**
     * Returns whether the parser should detect the delimiter automatically.
     *
     * @return whether the parser should detect the delimiter automatically
     */
    public boolean shouldDetectDelimiter() {
        return shouldDetectDelimiter;
    }

    /**
     * Sets whether the parser should detect the delimiter automatically.
     *
     * @param shouldDetectDelimiter whether the parser should detect the
     * delimiter automatically
     */
    public void setDetectDelimiter(boolean shouldDetectDelimiter) {
        this.shouldDetectDelimiter = shouldDetectDelimiter;
    }

    /**
     * Returns the delimiter used by this parser.
     *
     * @return the delimiter used by this parser
     */
    public char detectDelimiter(String line) {
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

    /**
     * Parses a line of CSV data into a CSVRow.
     *
     * @param line the line to parse
     * @return the parsed CSVRow
     * @throws CSVParseException if the data is invalid
     */
    public CSVRow parseLine(String line) throws CSVParseException {
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
                if (i + 1 < line.length() && line.charAt(i + 1) == getQuote()) {
                    cell.append(getQuote());
                    i++;
                } else {
                    inQuote = !inQuote;
                }
            } else if (c == getDelimiter() && !inQuote) {
                row.addCell(cell.toString());
                cell = new StringBuilder();
            } else {
                cell.append(c);
            }
        }
        row.addCell(cell.toString());
        if (inQuote) {
            throw new CSVParseException("Unclosed quote");
        }
        return row;
    }

    /**
     * Parses a table of CSV data into a CSVTable.
     *
     * @param table the table to parse
     * @return the parsed CSVTable
     * @throws CSVParseException if the data is invalid
     */
    public CSVTable parseTable(String table) throws CSVParseException {
        CSVTable csvTable = new CSVTable();

        // If newline is in \r\n or \r or \n format, split on those
        // Otherwise, split on the newline string
        String regex = "\r\n|\r|\n";
        if (!getNewline().matches(regex)) {
            regex = getNewline();
        }

        String[] lines = table.split(regex);
        int start = 0;
        if (lines.length > 0) {
            if (shouldDetectDelimiter()) {
                setDelimiter(detectDelimiter(lines[0]));
            }
            if (hasHeaders()) {
                csvTable.setHeaders(parseLine(lines[0]));
                start = 1;
            }
            for (int i = start; i < lines.length; i++) {
                CSVRow row = parseLine(lines[i]);
                csvTable.addRow(row);
            }
        }
        return csvTable;
    }
}
