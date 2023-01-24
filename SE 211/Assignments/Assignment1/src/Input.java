import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private final File inputFile;
    public Input(String filename) throws FileNotFoundException {
        inputFile = new File(filename);
        // If file does not exist, throw exception "Error: The file ${filename} does not exist"
        if (!inputFile.exists()) {
            throw new FileNotFoundException("The file " + filename + " does not exist");
        }
    }

    public ArrayList<String> readlines() throws FileNotFoundException {
        // Read lines from input file and add lines to lineStorage
        Scanner scanner = new Scanner(inputFile);
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine().trim();
            // If line is not empty, add it to the list
            if (!line.equals("")) {
                lines.add(line);
            }
        }
        return lines;
    }
}
