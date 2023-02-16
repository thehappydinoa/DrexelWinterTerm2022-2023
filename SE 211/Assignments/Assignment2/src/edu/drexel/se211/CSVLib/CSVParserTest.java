package edu.drexel.se211.CSVLib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
class CSVParser {
  ' Constants
  +{static}DEFAULT_DELIMITER: char = ','
  +{static}DEFAULT_QUOTE: char = '"'
  +{static}DEFAULT_ESCAPE: char = '\\'
  +{static}DEFAULT_HAS_HEADERS: boolean = false
  ' Fields
  -delimiter: char
  -quote: char
  -escape: char
  -hasHeaders: boolean
  ' Constructors
  +CSVParser()
  +CSVParser(char delimiter)
  +CSVParser(char delimiter, char quote)
  +CSVParser(char delimiter, char quote, char escape)
  +CSVParser(char delimiter, char quote, char escape, boolean hasHeaders)
  ' Methods
  +getDelimiter(): char
  +setDelimiter(char delimiter): void
  +getQuote(): char
  +setQuote(char quote): void
  +getEscape(): char
  +setEscape(char escape): void
  +hasHeaders(): boolean
  +setHasHeaders(boolean hasHeaders): void
  -detectDelimiter(String input): char
  +parseLine(String line): CSVRow
  +parseTable(String input): CSVTable
}
*/

class CSVParserTest {
    private CSVParser parser;

    @BeforeEach
    void setUp() {
        parser = new CSVParser();
    }

    @Test
    void testDefaultDelimiter() {
        assertEquals(',', parser.getDelimiter());
    }

    @Test
    void testDefaultQuote() {
        assertEquals('"', parser.getQuote());
    }

    @Test
    void testDefaultEscape() {
        assertEquals('\\', parser.getEscape());
    }

    @Test
    void testDefaultHasHeaders() {
        assertFalse(parser.hasHeaders());
    }

    @Test
    void testSetDelimiter() {
        parser.setDelimiter(';');
        assertEquals(';', parser.getDelimiter());
    }

    @Test
    void testSetQuote() {
        parser.setQuote('\'');
        assertEquals('\'', parser.getQuote());
    }

    @Test
    void testSetEscape() {
        parser.setEscape('!');
        assertEquals('!', parser.getEscape());
    }

    @Test
    void testSetHasHeaders() {
        parser.setHasHeaders(true);
        assertTrue(parser.hasHeaders());
    }

    @Test
    void testDetectDelimiter() {
        assertEquals(',', parser.detectDelimiter("a,b,c"));
        assertEquals(';', parser.detectDelimiter("a;b;c"));
        assertEquals('\t', parser.detectDelimiter("a\tb\tc"));
    }

    @Test
    void testDetectDelimiterWithQuotes() {
        assertEquals(',', parser.detectDelimiter("\"a\",\"b\",\"c\""));
        assertEquals(';', parser.detectDelimiter("\"a\";\"b\";\"c\""));
        assertEquals('\t', parser.detectDelimiter("\"a\"\t\"b\"\t\"c\""));
    }

    @Test
    void testDetectDelimiterWithEscapedQuotes() {
        assertEquals(',', parser.detectDelimiter("\"a\",\"b\",\"c\\\"\""));
        assertEquals(';', parser.detectDelimiter("\"a\";\"b\";\"c\\\"\""));
        assertEquals('\t', parser.detectDelimiter("\"a\"\t\"b\"\t\"c\\\"\""));
    }

    @Test
    void testParseLine() {
        CSVRow row = parser.parseLine("a,b,c");
        assertEquals(3, row.size());
        assertEquals("a", row.get(0));
        assertEquals("b", row.get(1));
        assertEquals("c", row.get(2));
    }

    @Test
    void testParseLineWithQuotes() {
        CSVRow row = parser.parseLine("\"a\",\"b\",\"c\"");
        assertEquals(3, row.size());
        assertEquals("a", row.get(0));
        assertEquals("b", row.get(1));
        assertEquals("c", row.get(2));
    }

    @Test
    void testParseLineWithEscapedQuotes() {
        CSVRow row = parser.parseLine("\"a\",\"b\",\"c\\\"\"");
        assertEquals(3, row.size());
        assertEquals("a", row.get(0));
        assertEquals("b", row.get(1));
        assertEquals("c\"", row.get(2));
    }

    @Test
    void testParseLineWithEscapedEscape() {
        CSVRow row = parser.parseLine("\"a\",\"b\",\"c\\\\\"");
        assertEquals(3, row.size());
        assertEquals("a", row.get(0));
        assertEquals("b", row.get(1));
        assertEquals("c\\", row.get(2));
    }

    @Test
    void testParseLineWithEscapedDelimiter() {
        CSVRow row = parser.parseLine("\"a\",\"b\",\"c\\,\"");
        assertEquals(3, row.size());
        assertEquals("a", row.get(0));
        assertEquals("b", row.get(1));
        assertEquals("c,", row.get(2));
    }
}