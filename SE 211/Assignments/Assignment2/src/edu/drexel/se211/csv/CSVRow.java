package edu.drexel.se211.csv;

import java.util.ArrayList;

public class CSVRow {
    // Fields
    private ArrayList<String> cells;

    // Constructors
    public CSVRow() {
        cells = new ArrayList<>();
    }
    public CSVRow(ArrayList<String> cells) {
        this.cells = cells;
    }

    // Methods
    public ArrayList<String> getCells() {
        return cells;
    }
    public int getCellCount() {
        return cells.size();
    }
    public void addCell(String cell) {
        cells.add(cell);
    }
    public void setCells(ArrayList<String> cells) {
        this.cells = cells;
    }
    public String getCell(int index) {
        return cells.get(index);
    }
    public void setCell(int index, String cell) {
        cells.set(index, cell);
    }
    public void removeCell(int index) {
        cells.remove(index);
    }
}
