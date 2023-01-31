import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInput extends Input {
    public static final String INPUT_TYPE = "file";
    private final String filename;
    private final Scanner scanner;

    public FileInput(String filename) throws FileNotFoundException {
        this.filename = filename;
        File file = new File(filename);
        this.scanner = new Scanner(file);
    }

    public ArrayList<String> readlines() {
        // Read file and return lines as an array
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    public KwicIndex read() throws FileNotFoundException {
        // Read file and return a KwicIndex
        return KwicIndex.fromFile(filename);
    }
}
