import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInput extends Input {
    private String filename;
    private File file;
    private Scanner scanner;

    public FileInput(String filename) throws FileNotFoundException {
        this.filename = filename;
        this.file = new File(filename);
        this.scanner = new Scanner(file);
    }

    public ArrayList<String> readlines() {
        // Read file and return lines as an array
        ArrayList<String> lines = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }
}
