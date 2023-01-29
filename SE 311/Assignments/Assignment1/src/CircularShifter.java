import java.util.ArrayList;

public class CircularShifter extends Shifter {
    @Override
    void shift(KwicIndex kwicIndex) {
        ArrayList<String> lines = kwicIndex.getSentences();
        // Add initial lines
        ArrayList<String> shiftedLines = new ArrayList<>(lines);
        // Loop through each line but skip adding the non-shifted line
        for (String line : lines) {
            String[] words = line.split(" ");
            // Loop through each word in the line
            for (int j = 0; j < words.length; j++) {
                // Shift the words
                StringBuilder shiftedLineBuilder = new StringBuilder();
                for (int k = 0; k < words.length; k++) {
                    shiftedLineBuilder.append(words[(j + k) % words.length]).append(" ");
                }
                String shiftedLine = shiftedLineBuilder.toString();
                shiftedLine = shiftedLine.trim();
                // If the first word is a stop word, skip it
                if (kwicIndex.isStopWord(shiftedLine.split(" ")[0])) {
                    continue;
                }
                // Add the shifted line to the list
                if (!shiftedLine.equals(line)) {
                    shiftedLines.add(shiftedLine);
                }
            }
        }
        kwicIndex.setSentences(shiftedLines);
    }
}
