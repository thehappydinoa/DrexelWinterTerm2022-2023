package edu.drexel.se211.CSVLib.ui;

import edu.drexel.se211.CSVLib.CSVReader;
import edu.drexel.se211.CSVLib.CSVTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class CSVFileSelector {
    private JFrame jframe;

    public CSVFileSelector() {
        jframe = createFrame();

        // Create and configure the file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a CSV file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);

        // Get the current working directory
        String currentDir = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(currentDir));

        // Show the file chooser when the button is clicked
        JButton openButton = new JButton("Open CSV File");
        openButton.addActionListener(event -> {
            int result = fileChooser.showOpenDialog(jframe);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    CSVReader reader = new CSVReader(filename);
                    reader.setHasHeader(true);
                    CSVTable table = reader.readTable();
                    new CSVTableInterface(table, filename);
                    jframe.dispose(); // close the file selector window
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(jframe, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jframe.add(openButton, BorderLayout.CENTER);

        // Show the window
        showFrame(jframe);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("CSV File Selector");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private void showFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CSVFileSelector();
    }
}
