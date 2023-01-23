import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
    private File inputFile;
    public Input(String filename) throws FileNotFoundException {
        inputFile = new File(filename);
        // If file does not exist, throw exception "Error: The file ${filename} does not exist"
        if (!inputFile.exists()) {
            throw new FileNotFoundException("The file " + filename + " does not exist");
        }
    }

    public void addLines(LineStorage lineStorage) throws FileNotFoundException {
        // Read lines from input file and add lines to lineStorage
        Scanner scanner = new Scanner(inputFile);
        while (scanner.hasNextLine()) {
            lineStorage.addLine(scanner.nextLine());
        }
    }

}
