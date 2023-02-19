package edu.drexel.se211.CSVLib.ui;

import edu.drexel.se211.CSVLib.CSVParser;

import javax.swing.*;
import java.awt.*;

public class CSVPreferences {
    private final JFrame jframe;
    private final CSVParser parser;

    public CSVPreferences(CSVParser parser) {
        jframe = createFrame();
        this.parser = parser;

        // Create the panel
        createPanel();

        // Show the window
        showFrame();
    }

    private void createPanel() {
        // Create a new panel to hold the input fields with plenty of space
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        // Add some space between the input fields
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Dimension minCharFieldSize = new Dimension(20, 20);
        Dimension maxCharFieldSize = new Dimension(40, 20);

        // Create a row for the delimiter and detect delimiter checkbox
        JPanel delimiterPanel = new JPanel();
        delimiterPanel.setLayout(new BoxLayout(delimiterPanel, BoxLayout.X_AXIS));
        delimiterPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Create the delimiter input field
        JLabel delimiterLabel = new JLabel("Delimiter");
        delimiterPanel.add(delimiterLabel);
        JTextField delimiterField = new JTextField(String.valueOf(parser.getDelimiter()));
        delimiterField.setMinimumSize(minCharFieldSize);
        delimiterField.setMaximumSize(maxCharFieldSize);
        delimiterField.addActionListener(event -> {
            parser.setDelimiter(delimiterField.getText().charAt(0));
            delimiterField.setText(String.valueOf(parser.getDelimiter()));
        });
        delimiterPanel.add(delimiterField);

        // Create the "should detect delimiter" checkbox
        JCheckBox detectDelimiterCheckbox = new JCheckBox("Detect Delimiter");
        detectDelimiterCheckbox.setSelected(parser.shouldDetectDelimiter());
        detectDelimiterCheckbox.addActionListener(event -> parser.setDetectDelimiter(detectDelimiterCheckbox.isSelected()));
        delimiterPanel.add(detectDelimiterCheckbox);

        // Add the delimiter row to the panel
        panel.add(delimiterPanel);

        // Create a row for the quote and escape input fields
        JPanel quoteEscapePanel = new JPanel();
        quoteEscapePanel.setLayout(new BoxLayout(quoteEscapePanel, BoxLayout.X_AXIS));
        quoteEscapePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Create the quote input field
        JLabel quoteLabel = new JLabel("Quote");
        quoteEscapePanel.add(quoteLabel);
        JTextField quoteField = new JTextField(String.valueOf(parser.getQuote()));
        quoteField.setMinimumSize(minCharFieldSize);
        quoteField.setMaximumSize(maxCharFieldSize);
        quoteField.addActionListener(event -> {
            parser.setQuote(quoteField.getText().charAt(0));
            quoteField.setText(String.valueOf(parser.getQuote()));
        });
        quoteEscapePanel.add(quoteField);

        // Create the escape input field
        JLabel escapeLabel = new JLabel("Escape");
        quoteEscapePanel.add(escapeLabel);
        JTextField escapeField = new JTextField(String.valueOf(parser.getEscape()));
        escapeField.setMinimumSize(minCharFieldSize);
        escapeField.setMaximumSize(maxCharFieldSize);
        escapeField.addActionListener(event -> {
            parser.setEscape(escapeField.getText().charAt(0));
            escapeField.setText(String.valueOf(parser.getEscape()));
        });
        quoteEscapePanel.add(escapeField);

        // Add the quote and escape row to the panel
        panel.add(quoteEscapePanel);

        // Create a row for the null and newline input fields
        JPanel nullNewlinePanel = new JPanel();
        nullNewlinePanel.setLayout(new BoxLayout(nullNewlinePanel, BoxLayout.X_AXIS));
        nullNewlinePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Create the null input field
        JLabel nullLabel = new JLabel("Null Value");
        nullNewlinePanel.add(nullLabel);
        JTextField nullField = new JTextField(String.valueOf(parser.getNullValue()));
        nullField.setMinimumSize(minCharFieldSize);
        nullField.setMaximumSize(maxCharFieldSize);
        nullField.addActionListener(event -> {
            parser.setNullValue(nullField.getText());
            nullField.setText(String.valueOf(parser.getNullValue()));
        });
        nullNewlinePanel.add(nullField);

        // Create the newline input field
        JLabel newlineLabel = new JLabel("Newline");
        nullNewlinePanel.add(newlineLabel);
        JTextField newlineField = new JTextField(getNewline());
        newlineField.setMinimumSize(minCharFieldSize);
        newlineField.setMaximumSize(maxCharFieldSize);
        newlineField.addActionListener(event -> {
            setNewline(newlineField.getText());
            newlineField.setText(getNewline());
        });
        nullNewlinePanel.add(newlineField);

        // Add the null and newline row to the panel
        panel.add(nullNewlinePanel);

        // Add the panel to the frame
        jframe.add(panel, BorderLayout.CENTER);
    }

    private JFrame createFrame() {
        JFrame jframe = new JFrame("CSV Preferences");
        jframe.setMinimumSize(new Dimension(300, 150));
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return jframe;
    }

    private void showFrame() {
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private String getNewline() {
        return parser.getNewline().replace("\r", "\\r").replace("\n", "\\n");
    }

    private void setNewline(String newline) {
        parser.setNewline(newline.replace("\\r", "\r").replace("\\n", "\n"));
    }
}
