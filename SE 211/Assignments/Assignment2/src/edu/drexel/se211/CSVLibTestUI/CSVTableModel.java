package edu.drexel.se211.CSVLibTestUI;

import edu.drexel.se211.CSVLib.CSVRow;
import edu.drexel.se211.CSVLib.CSVTable;

import javax.swing.table.AbstractTableModel;
import java.io.Serial;

public class CSVTableModel extends AbstractTableModel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final CSVTable table;

    public CSVTableModel(CSVTable table) {
        this.table = table;
    }

    @Override
    public int getColumnCount() {
        return table.getLongestRow();
    }

    @Override
    public int getRowCount() {
        return table.getRowSize();
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        return table.getRow(rowIndex).getCell(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return table.getHeaders().getCell(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        table.getRow(rowIndex).setCell(columnIndex, (String) aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addRow() {
        table.addRow(new CSVRow(table.getLongestRow()));
        int row = table.getRowSize() - 1;
        fireTableRowsInserted(row, row);
    }

    public void removeRow(int row) {
        table.removeRow(row);
        fireTableRowsDeleted(row, row);
    }
}
