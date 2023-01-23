import java.util.ArrayList;

public class CircularShift {
    public static void shift(LineStorage lineStorage) {
        ArrayList<String> lines = lineStorage.getLines();
        ArrayList<String> shiftedLines = new ArrayList<>();
        // Add initial lines
        shiftedLines.addAll(lines);
        // Loop through each line but skip adding the non-shifted line
        for (int i = 0; i < lines.size(); i++) {
            String[] words = lines.get(i).split(" ");
            // Loop through each word in the line
            for (int j = 0; j < words.length; j++) {
                // Shift the words
                String shiftedLine = "";
                for (int k = 0; k < words.length; k++) {
                    shiftedLine += words[(j + k) % words.length] + " ";
                }
                shiftedLine = shiftedLine.trim();
                // Add the shifted line to the list
                if (!shiftedLine.equals(lines.get(i))) {
                    shiftedLines.add(shiftedLine);
                }
            }
        }
        lineStorage.setLines(shiftedLines);
    }
}
