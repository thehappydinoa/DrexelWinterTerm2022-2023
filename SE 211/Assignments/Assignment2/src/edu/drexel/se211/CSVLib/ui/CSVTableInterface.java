package edu.drexel.se211.CSVLib.ui;

import edu.drexel.se211.CSVLib.CSVRow;
import edu.drexel.se211.CSVLib.CSVTable;
import edu.drexel.se211.CSVLib.CSVWriter;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CSVTableInterface {
    private CSVTable table;
    private JFrame jframe;
    private JTable jtable;
    private JButton saveButton;
    private String filename;

    public CSVTableInterface(CSVTable table, String filename) {
        this.table = table;
        this.filename = filename;
        jframe = createFrame();

        // Create table model
        DefaultTableModel model = new DefaultTableModel(table.to2DArray(), table.getHeaders().getCells().toArray());

        // Create table
        jtable = new JTable(model);
        jtable.setFillsViewportHeight(true);

        // Update table when cell is edited
        jtable.getModel().addTableModelListener(event -> {
            int row = event.getFirstRow();
//            row = row - 1;
//            if (table.hasHeaders()) {
//                row = row - 1;
//            }
            int column = event.getColumn();
            int eventType = event.getType();
            if (eventType != TableModelEvent.INSERT) {
                if (row > table.getRowCount() - 1) {
                    table.addRow(new CSVRow());
                } else if (column == -1) {
                    table.addRow(new CSVRow());
                } else {
                    String value = (String) jtable.getModel().getValueAt(row, column);
                    table.getRow(row).setCell(column, value);
                }
            } else if (eventType == TableModelEvent.UPDATE) {
                String value = (String) jtable.getModel().getValueAt(row, column);
                table.getRow(row).setCell(column, value);
            } else if (eventType == TableModelEvent.DELETE) {
                table.removeRow(row);
            }
        });

        // Add right-click menu to JTable
        JPopupMenu popupMenu = new JPopupMenu();

        // Add row
        JMenuItem addRowItem = new JMenuItem("Add row");
        addRowItem.addActionListener(event -> addRow());
        popupMenu.add(addRowItem);

        // Delete row
        JMenuItem deleteRowItem = new JMenuItem("Delete row");
        deleteRowItem.addActionListener(event -> deleteRow());
        popupMenu.add(deleteRowItem);

        // Add mouse listener to JTable
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = jtable.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        jtable.setRowSelectionInterval(row, row);
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        // Add table to frame
        jframe.add(new JScrollPane(jtable));

        // Save button
        saveButton = new JButton("Save");
        saveButton.addActionListener(event -> saveFile());
        jframe.add(saveButton, BorderLayout.SOUTH);

        // Show frame
        showFrame(jframe);
    }

    private void addRow() {
        DefaultTableModel model1 = (DefaultTableModel) jtable.getModel();
        model1.addRow(new Object[]{});
    }

    private void deleteRow() {
        int selectedRow = jtable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model1 = (DefaultTableModel) jtable.getModel();
            model1.removeRow(selectedRow);
        }
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("CSV Table");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private void showFrame(JFrame frame) {
        frame.pack();
        frame.setVisible(true);
    }

    private void saveFile() {
        try {
            CSVWriter writer = new CSVWriter(filename);
            writer.writeTable(table);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

