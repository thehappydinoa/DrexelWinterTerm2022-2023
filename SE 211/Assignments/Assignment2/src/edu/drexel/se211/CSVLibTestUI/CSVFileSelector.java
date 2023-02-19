package edu.drexel.se211.CSVLibTestUI;

import edu.drexel.se211.CSVLib.CSVParser;
import edu.drexel.se211.CSVLib.CSVReader;
import edu.drexel.se211.CSVLib.CSVTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A CSVFileSelector allows the user to select a CSV file to open.
 */
public class CSVFileSelector {
    private final JFrame jframe;
    private final CSVParser parser;

    /**
     * Creates a new CSVFileSelector.
     */
    public CSVFileSelector() {
        jframe = createFrame();
        parser = new CSVParser();

        // Create the menu bar
        JMenuBar menuBar = createMenuBar();
        jframe.setJMenuBar(menuBar);

        // Create a new centered panel to hold the button
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel("Select a CSV file to open:"));

        // Create a new button
        JButton openButton = new JButton("Open CSV File");
        openButton.addActionListener(event -> openFile(getjFileChooser()));
        panel.add(openButton);

        // Add the panel to the frame
        jframe.add(panel, BorderLayout.CENTER);

        // Show the window
        showFrame(jframe);
    }

    /**
     * Creates a new JFileChooser.
     */
    private static JFileChooser getjFileChooser() {
        // Create and configure the file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a CSV file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);

        // Get the current working directory
        String currentDir = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(currentDir));
        return fileChooser;
    }

    /**
     * Creates a new CSVFileSelector.
     */
    private JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("CSV File Selector");
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(400, 120));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    /**
     * Creates a new MenuBar.
     */
    private JMenuBar createMenuBar() {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the File menu
        JMenu menu = new JMenu("File");

        // Create the Open menu item
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke("meta O"));
        openItem.addActionListener(event -> openFile(getjFileChooser()));
        menu.add(openItem);

        // Create the Preferences menu item
        JMenuItem preferencesItem = new JMenuItem("Preferences");
        preferencesItem.setAccelerator(KeyStroke.getKeyStroke("meta COMMA"));
        preferencesItem.addActionListener(event -> openPreferences());
        menu.add(preferencesItem);

        // Add the File menu to the menu bar
        menuBar.add(menu);

        return menuBar;
    }

    /**
     * Shows the given frame.
     */
    private void showFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Prompts the user to select a CSV file and opens it.
     */
    private void openFile(JFileChooser fileChooser) {
        int result = fileChooser.showOpenDialog(jframe);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                CSVReader reader = new CSVReader(filename, parser);
                reader.setHasHeader(true);
                CSVTable table = reader.readTable();
                new CSVTableInterface(table, filename);
                jframe.dispose(); // close the file selector window
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(jframe, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(jframe, "I/O error", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(jframe, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CSVParser getParser() {
        return parser;
    }

    /**
     * Opens the CSVPreferences window.
     */
    private void openPreferences() {
        new CSVPreferences(getParser());
    }

    public static File selectFile(JFrame frame) {
        JFileChooser fileChooser = getjFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }
}
