import edu.drexel.se211.CSVLib.CSVParseException;
import edu.drexel.se211.CSVLib.CSVParser;
import edu.drexel.se211.CSVLib.CSVRow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVParserTest {
    private CSVParser parser;

    @BeforeEach
    void setUp() {
        parser = new CSVParser();
    }

    @Test
    void testDefaultDelimiter() {
        Assertions.assertEquals(',', parser.getDelimiter());
    }

    @Test
    void testDefaultQuote() {
        Assertions.assertEquals('"', parser.getQuote());
    }

    @Test
    void testDefaultEscape() {
        Assertions.assertEquals('\\', parser.getEscape());
    }

    @Test
    void testDefaultHasHeaders() {
        Assertions.assertFalse(parser.hasHeaders());
    }

    @Test
    void testSetDelimiter() {
        parser.setDelimiter(';');
        Assertions.assertEquals(';', parser.getDelimiter());
    }

    @Test
    void testSetQuote() {
        parser.setQuote('\'');
        Assertions.assertEquals('\'', parser.getQuote());
    }

    @Test
    void testSetEscape() {
        parser.setEscape('!');
        Assertions.assertEquals('!', parser.getEscape());
    }

    @Test
    void testSetHasHeaders() {
        parser.setHasHeaders(true);
        Assertions.assertTrue(parser.hasHeaders());
    }

    @Test
    void testDetectDelimiter() {
        Assertions.assertEquals(',', parser.detectDelimiter("a,b,c"));
        Assertions.assertEquals(';', parser.detectDelimiter("a;b;c"));
        Assertions.assertEquals('\t', parser.detectDelimiter("a\tb\tc"));
    }

    @Test
    void testDetectDelimiterWithQuotes() {
        Assertions.assertEquals(',', parser.detectDelimiter("\"a\",\"b\",\"c\""));
        Assertions.assertEquals(';', parser.detectDelimiter("\"a\";\"b\";\"c\""));
        Assertions.assertEquals('\t', parser.detectDelimiter("\"a\"\t\"b\"\t\"c\""));
    }

    @Test
    void testDetectDelimiterWithEscapedQuotes() {
        Assertions.assertEquals(',', parser.detectDelimiter("\"a\",\"b\",\"c\\\"\""));
        Assertions.assertEquals(';', parser.detectDelimiter("\"a\";\"b\";\"c\\\"\""));
        Assertions.assertEquals('\t', parser.detectDelimiter("\"a\"\t\"b\"\t\"c\\\"\""));
    }

    @Test
    void testParseLine() throws CSVParseException {
        CSVRow row = parser.parseLine("a,b,c");
        Assertions.assertEquals(3, row.size());
        Assertions.assertEquals("a", row.get(0));
        Assertions.assertEquals("b", row.get(1));
        Assertions.assertEquals("c", row.get(2));
    }

    @Test
    void testParseLineWithQuotes() throws CSVParseException {
        CSVRow row = parser.parseLine("\"a\",\"b\",\"c\"");
        Assertions.assertEquals(3, row.size());
        Assertions.assertEquals("a", row.get(0));
        Assertions.assertEquals("b", row.get(1));
        Assertions.assertEquals("c", row.get(2));
    }

    @Test
    void testParseLineWithEscapedQuotes() throws CSVParseException {
        CSVRow row = parser.parseLine("\"a\",\"b\",\"c\\\"\"");
        Assertions.assertEquals(3, row.size());
        Assertions.assertEquals("a", row.get(0));
        Assertions.assertEquals("b", row.get(1));
        Assertions.assertEquals("c\"", row.get(2));
    }

    @Test
    void testParseLineWithEscapedEscape() throws CSVParseException {
        CSVRow row = parser.parseLine("\"a\",\"b\",\"c\\\\\"");
        Assertions.assertEquals(3, row.size());
        Assertions.assertEquals("a", row.get(0));
        Assertions.assertEquals("b", row.get(1));
        Assertions.assertEquals("c\\", row.get(2));
    }

    @Test
    void testParseLineWithEscapedDelimiter() throws CSVParseException {
        CSVRow row = parser.parseLine("\"a\",\"b\",\"c\\,\"");
        Assertions.assertEquals(3, row.size());
        Assertions.assertEquals("a", row.get(0));
        Assertions.assertEquals("b", row.get(1));
        Assertions.assertEquals("c,", row.get(2));
    }
}