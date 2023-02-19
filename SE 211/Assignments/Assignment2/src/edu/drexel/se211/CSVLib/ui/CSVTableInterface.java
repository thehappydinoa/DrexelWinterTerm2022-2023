package edu.drexel.se211.CSVLib.ui;

import edu.drexel.se211.CSVLib.CSVReader;
import edu.drexel.se211.CSVLib.CSVRow;
import edu.drexel.se211.CSVLib.CSVTable;
import edu.drexel.se211.CSVLib.CSVWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import static javax.swing.event.TableModelEvent.*;


public class CSVTableInterface {
    private final CSVTable table;
    private final JFrame jframe;
    private final JTable jtable;
    private final String filename;

    public CSVTableInterface(CSVTable table, String filename) {
        this.table = table;
        this.filename = filename;
        jframe = createFrame();

        // Create the menu bar
        JMenuBar menuBar = createMenuBar();
        jframe.setJMenuBar(menuBar);

        // Create table model
        DefaultTableModel model = new DefaultTableModel(table.to2DArray(), table.getHeaders().getCells().toArray());

        // Create table
        jtable = new JTable(model);
        jtable.setFillsViewportHeight(true);

        // Update table when cell is edited
        jtable.getModel().addTableModelListener(event -> {
            int row = event.getFirstRow();
            int column = event.getColumn();
            int eventType = event.getType();
            if (eventType != INSERT) {
                if (row > table.getRowCount() - 1) {
                    table.addRow(new CSVRow());
                } else if (column == -1) {
                    table.addRow(new CSVRow());
                } else {
                    String value = (String) jtable.getModel().getValueAt(row, column);
                    table.getRow(row).setCell(column, value);
                }
            } else //noinspection ConstantValue
                if (eventType == UPDATE) {
                String value = (String) jtable.getModel().getValueAt(row, column);
                table.getRow(row).setCell(column, value);
            } else //noinspection ConstantValue
                    if (eventType == DELETE) {
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

        // Add select cell on click
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtable.rowAtPoint(e.getPoint());
                int col = jtable.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    jtable.setRowSelectionInterval(row, row);
                    jtable.setColumnSelectionInterval(col, col);
                }
            }
        });

        // Add table to frame
        jframe.add(new JScrollPane(jtable));

        // Show frame
        showFrame(jframe);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Create file menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Create open menu item
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke("meta O"));
        openItem.addActionListener(event -> openFile());
        fileMenu.add(openItem);

        // Create save menu item
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke("meta S"));
        saveItem.addActionListener(event -> saveFile());
        fileMenu.add(saveItem);

        // Create edit menu
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        // Create add row menu item
        JMenuItem addRowItem = new JMenuItem("Add row");
        addRowItem.addActionListener(event -> addRow());
        editMenu.add(addRowItem);

        // Create delete row menu item
        JMenuItem deleteRowItem = new JMenuItem("Delete row");
        deleteRowItem.addActionListener(event -> deleteRow());
        editMenu.add(deleteRowItem);

        // Create help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // Create about menu item
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(event -> JOptionPane.showMessageDialog(jframe, "CSV Table Editor\nVersion 1.0\nCreated by: Aidan Holland (adh345)"));
        helpMenu.add(aboutItem);

        return menuBar;
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
        String title = "CSV Table Editor";
        if (filename != null) {
            title += " - " + new File(filename).getName();
        }
        frame.setTitle(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private void showFrame(JFrame frame) {
        frame.pack();
        frame.setVisible(true);
    }

    private void openFile() {
        try {
            File file = CSVFileSelector.selectFile(jframe);
            if (file != null) {
                CSVReader reader = new CSVReader(file.getAbsolutePath());
                reader.setHasHeader(true);
                CSVTable table = reader.readTable();
                new CSVTableInterface(table, file.getAbsolutePath());
                jframe.dispose();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

