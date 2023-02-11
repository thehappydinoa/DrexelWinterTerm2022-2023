package edu.drexel.se211.csv;

import java.util.ArrayList;

public class CSVRow {
    // Fields
    private ArrayList<String> cells;

    // Constructors
    public CSVRow() {
        this(new ArrayList<>());
    }
    public CSVRow(ArrayList<String> cells) {
        this.cells = cells;
    }

    // Methods
    public ArrayList<String> getCells() {
        return cells;
    }
    public int size() {
        return cells.size();
    }
    public void addCell(String cell) {
        cells.add(cell);
    }
    public void setCells(ArrayList<String> cells) {
        this.cells = cells;
    }
    public String get(int index) {
        return getCell(index);
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
    public void print(boolean bold) {
        StringBuilder sb = new StringBuilder();
        for (String cell : cells) {
            if (bold) {
                sb.append("\033[1m" + cell + "\033[0m");
            } else {
                sb.append(cell);
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    public void print() {
        print(false);
    }
}
