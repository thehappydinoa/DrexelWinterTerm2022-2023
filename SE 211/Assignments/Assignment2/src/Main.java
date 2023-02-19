import edu.drexel.se211.CSVLibTestUI.CSVFileSelector;

import javax.swing.UIManager;

/**
 * The main class for the CSVLibTestUI application.
 * 
 * @author Aidan Holland (adh346)
 */
public class Main {
    public static void main(String[] args) {
        // Take the menu bar off the frame
        System.setProperty("com.apple.macos.useScreenMenuBar", "true");
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        // Set the name of the application menu item
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "CSVLib");

        // Set the look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Open the file selector
        new CSVFileSelector();
    }
}