package edu.drexel.se211.CSVLibTestUI;

import edu.drexel.se211.CSVLib.CSVReader;
import edu.drexel.se211.CSVLib.CSVTable;
import edu.drexel.se211.CSVLib.CSVWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Objects;


public class CSVTableInterface {
    private final CSVTable table;
    private final JFrame jframe;
    private final JTable jtable;
    private final String filename;
    private boolean isSaved = true;

    public CSVTableInterface(CSVTable table, String filename) {
        this.table = table;
        this.filename = filename;
        jframe = createFrame();

        // Create the menu bar
        JMenuBar menuBar = createMenuBar();
        jframe.setJMenuBar(menuBar);

        // Create table model
        CSVTableModel model = new CSVTableModel(table);

        // Create table
        jtable = new JTable(model);
        jtable.setFillsViewportHeight(true);

        // Listen for changes to the table
        model.addTableModelListener(event -> setSaved(false));

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
                } else {
                    jtable.clearSelection();
                }
            }
        });

        // Add table to frame
        jframe.add(new JScrollPane(jtable));

        // Show frame
        showFrame(jframe);
    }

    private void updateTitle(JFrame jframe) {
        String title = "CSV Table Editor";
        if (filename != null) {
            title += " - " + new File(filename).getName();
        }
        if (!isSaved()) {
            title += "*";
        }
        if (jframe != null && !Objects.equals(jframe.getTitle(), title)) {
            jframe.setTitle(title);
        }
    }

    private void updateTitle() {
        updateTitle(jframe);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame();
        updateTitle(frame);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private void showFrame(JFrame frame) {
        frame.pack();
        frame.setVisible(true);
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

        // Create select all menu item
        JMenuItem selectAllItem = new JMenuItem("Select all");
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke("meta A"));
        selectAllItem.addActionListener(event -> jtable.selectAll());
        editMenu.add(selectAllItem);

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
        CSVTableModel model = (CSVTableModel) jtable.getModel();
        model.addRow();
        // Select the new row
        int lastRow = model.getRowCount() - 1;
        jtable.setRowSelectionInterval(lastRow, lastRow);
    }

    private void deleteRow() {
        int selectedRow = jtable.getSelectedRow();
        if (selectedRow >= 0) {
            CSVTableModel model = (CSVTableModel) jtable.getModel();
            model.removeRow(selectedRow);
            // Select the row above the deleted row
            int newSelectedRow = selectedRow - 1;
            if (newSelectedRow >= 0) {
                jtable.setRowSelectionInterval(newSelectedRow, newSelectedRow);
            }
        }
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
            setSaved(true);
            // Unselect all cells
            jtable.clearSelection();
            jtable.repaint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isSaved() {
        return isSaved;
    }

    private void setSaved(boolean isSaved) {
        this.isSaved = isSaved;
        updateTitle();
    }
}

