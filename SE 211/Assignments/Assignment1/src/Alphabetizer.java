import java.util.ArrayList;

public class Alphabetizer {
    public static void sort(LineStorage lineStorage) {
        ArrayList<String> lines = lineStorage.getLines();
        // Sort lines
        for (int i = 0; i < lines.size() - 1; i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                if (lines.get(i).compareTo(lines.get(j)) > 0) {
                    String temp = lines.get(i);
                    lines.set(i, lines.get(j));
                    lines.set(j, temp);
                }
            }
        }
        lineStorage.setLines(lines);
    }
}
