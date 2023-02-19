package edu.drexel.se211.CSVLib;

/**
 * Exception thrown when a CSV file cannot be parsed.
 */
public class CSVParseException extends Exception {
    public CSVParseException(String message) {
        super(message);
    }
}
