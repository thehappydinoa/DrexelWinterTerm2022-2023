package edu.drexel.se211.CSVLib.ui;

import edu.drexel.se211.CSVLib.CSVReader;
import edu.drexel.se211.CSVLib.CSVRow;
import edu.drexel.se211.CSVLib.CSVTable;
import edu.drexel.se211.CSVLib.CSVWriter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Table {
    private CSVTable table;
    private JFrame jframe;
    private JTable jtable;
    private JButton saveButton;

    public Table(CSVTable table) {
        this.table = table;
        jframe = createFrame();

        // Create table model
        DefaultTableModel model = new DefaultTableModel(table.to2DArray(), table.getHeaders().getCells().toArray());

        // Create table
        jtable = new JTable(model);
        jtable.setFillsViewportHeight(true);

        // Add row button
        JButton addRowButton = new JButton("Add Row");
        addRowButton.addActionListener(event -> {
            model.addRow(new String[table.getRow(0).getCells().size()]);
            model.fireTableDataChanged();
        });
        jframe.add(addRowButton, BorderLayout.NORTH);

        // Update table when cell is edited
        jtable.getModel().addTableModelListener(event -> {
            int row = event.getFirstRow();
            int column = event.getColumn();
            int eventType = event.getType();
            if (eventType != TableModelEvent.INSERT) {
                if (column == -1) {
                    table.addRow(new CSVRow());
                } else {
                    String value = (String) jtable.getModel().getValueAt(row, column);
                    table.getRow(row).setCell(column, value);
                }
            } else if (eventType == TableModelEvent.UPDATE) {
                String value = (String) jtable.getModel().getValueAt(row, column);
                table.getRow(row).setCell(column, value);
            }
        });
        jframe.add(new JScrollPane(jtable));

        // Save button
        saveButton = new JButton("Save");
        saveButton.addActionListener(event -> {
            try {
                CSVWriter writer = new CSVWriter("example.csv");
                writer.writeTable(table);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        jframe.add(saveButton, BorderLayout.SOUTH);

        // Show frame
        showFrame(jframe);
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

    public static void main(String[] args) {
        String filename = "example.csv";
        try {
            CSVReader reader = new CSVReader(filename);
            reader.setHasHeader(true);
            CSVTable table = reader.readTable();
            new Table(table);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
