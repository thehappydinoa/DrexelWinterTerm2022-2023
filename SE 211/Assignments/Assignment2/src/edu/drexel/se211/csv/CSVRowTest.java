package edu.drexel.se211.csv;

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

        assertEquals(2, csvRow.getCellCount());
    }

    @Test
    void testAddCell() {
        csvRow.addCell("cell1");

        assertEquals(1, csvRow.getCellCount());
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

        assertEquals(1, csvRow.getCellCount());
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
}
