package CSVLibrary;

public class CSVParser {
    // Constants
    public static final char DEFAULT_DELIMITER = ',';
    public static final char DEFAULT_QUOTE = '"';
    public static final char DEFAULT_ESCAPE = '\\';

    // Fields
    private char delimiter;
    private char quote;
    private char escape;

    // Constructors
    public CSVParser() {
        this(DEFAULT_DELIMITER, DEFAULT_QUOTE, DEFAULT_ESCAPE);
    }
    public CSVParser(char delimiter) {
        this(delimiter, DEFAULT_QUOTE, DEFAULT_ESCAPE);
    }
    public CSVParser(char delimiter, char quote) {
        this(delimiter, quote, DEFAULT_ESCAPE);
    }
    public CSVParser(char delimiter, char quote, char escape) {
        this.delimiter = delimiter;
        this.quote = quote;
        this.escape = escape;
    }

    // Methods
    private char automaticDelimiter(String line) {
        int commaCount = 0;
        int semicolonCount = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ',') {
                commaCount++;
            } else if (c == ';') {
                semicolonCount++;
            }
        }
        if (commaCount > semicolonCount) {
            return ',';
        } else {
            return ';';
        }
    }
    public CSVRow parseLine(String line, boolean hasHeader) {
        if (hasHeader) {
            return parseLineWithHeader(line);
        } else {
            return parseLine(line);
        }
    }
    public CSVRow parseLine(String line) {
        CSVRow row = new CSVRow();
        StringBuilder cell = new StringBuilder();
        boolean inQuote = false;
        boolean inEscape = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inEscape) {
                cell.append(c);
                inEscape = false;
            } else if (c == escape) {
                inEscape = true;
            } else if (c == quote) {
                inQuote = !inQuote;
            } else if (c == delimiter && !inQuote) {
                row.addCell(cell.toString());
                cell = new StringBuilder();
            } else {
                cell.append(c);
            }
        }
        row.addCell(cell.toString());
        return row;
    }
    public CSVRowWithHeader parseLineWithHeader(String line) {
        CSVRowWithHeader row = new CSVRowWithHeader();
        StringBuilder cell = new StringBuilder();
        boolean inQuote = false;
        boolean inEscape = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inEscape) {
                cell.append(c);
                inEscape = false;
            } else if (c == escape) {
                inEscape = true;
            } else if (c == quote) {
                inQuote = !inQuote;
            } else if (c == delimiter && !inQuote) {
                row.addCell(cell.toString());
                cell = new StringBuilder();
            } else {
                cell.append(c);
            }
        }
        row.addCell(cell.toString());
        return row;
    }
    public CSVTable parseTable(String table) {
        CSVTable csvTable = new CSVTable();
        String[] lines = table.split("\r\n|\r|\n");
        char delimiter = automaticDelimiter(lines[0]);
        CSVRowWithHeader header = parseLineWithHeader(lines[0]);
        csvTable.setHeader(header);
        for (int i = 1; i < lines.length; i++) {
            CSVRow row = parseLine(lines[i]);
            csvTable.addRow(row);
        }
        return csvTable;
    }
}
