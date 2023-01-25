import java.util.ArrayList;

public class Alphabetizer {
    public static void sort(LineStorage lineStorage) {
        ArrayList<String> lines = lineStorage.getLines();
        // Sort lines case-insensitive
        lines.sort(String::compareToIgnoreCase);
        lineStorage.setLines(lines);
    }
}
