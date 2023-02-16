package edu.drexel.se211.CSVLib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVRowTest {

    private CSVRow csvRow;

    @BeforeEach
    void setUp() {
        csvRow = new CSVRow();
    }

    @Test
    void testGetCells() {
        ArrayList<String> cells = new ArrayList<>();
        cells.add("cell1");
        cells.add("cell2");
        csvRow.setCells(cells);

        assertEquals(cells, csvRow.getCells());
    }

    @Test
    void testGetCellCount() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell2");

        assertEquals(2, csvRow.size());
    }

    @Test
    void testAddCell() {
        csvRow.addCell("cell1");

        assertEquals(1, csvRow.size());
        assertEquals("cell1", csvRow.getCell(0));
    }

    @Test
    void testSetCells() {
        ArrayList<String> cells = new ArrayList<>();
        cells.add("cell1");
        cells.add("cell2");
        csvRow.setCells(cells);

        assertEquals(cells, csvRow.getCells());
    }

    @Test
    void testGetCell() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell2");

        assertEquals("cell1", csvRow.getCell(0));
        assertEquals("cell2", csvRow.getCell(1));
    }

    @Test
    void testSetCell() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell2");
        csvRow.setCell(1, "cell3");

        assertEquals("cell3", csvRow.getCell(1));
    }

    @Test
    void testRemoveCell() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell2");
        csvRow.removeCell(1);

        assertEquals(1, csvRow.size());
        assertEquals("cell1", csvRow.getCell(0));
    }

    @Test
    void testRemoveCellThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> csvRow.removeCell(0));
    }

    @Test
    void testGetCellThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> csvRow.getCell(0));
    }

    @Test
    void testToString() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell2");

        assertEquals("cell1,cell2", csvRow.toString(',', '"', '\\'));
    }

    @Test
    void testToStringWithNullCell() {
        csvRow.addCell("cell1");
        csvRow.addCell(null);
        csvRow.addCell("cell2");

        assertEquals("cell1,,cell2", csvRow.toString(',', '"', '\\'));
    }

    @Test
    void testToStringWithDelimiterInCell() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell,2");
        csvRow.addCell("cell2");

        assertEquals("cell1,\"cell,2\",cell2", csvRow.toString(',', '"', '\\'));
    }

    @Test
    void testToStringWithQuoteInCell() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell\"2");
        csvRow.addCell("cell2");

        assertEquals("cell1,\"cell\\\"2\",cell2", csvRow.toString(',', '"', '\\'));
    }

    @Test
    void testToStringWithEscapeInCell() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell\\2");
        csvRow.addCell("cell2");

        assertEquals("cell1,\"cell\\\\2\",cell2", csvRow.toString(',', '"', '\\'));
    }

    @Test
    void testToStringForEmptyRow() {
        assertEquals("", csvRow.toString());
    }

    @Test
    void testToStringWithEmptyRowMostCells() {
        csvRow.addCell("cell1");
        csvRow.addCell("");
        csvRow.addCell("cell2");

        assertEquals("cell1,,cell2,", csvRow.toString(',', '"', '\\', 4));
    }

    @Test
    void testToStringFromParser() {
        csvRow.addCell("cell1");
        csvRow.addCell("cell2");

        assertEquals("cell1,cell2", csvRow.toString(',', '"', '\\', 2));
    }
}
