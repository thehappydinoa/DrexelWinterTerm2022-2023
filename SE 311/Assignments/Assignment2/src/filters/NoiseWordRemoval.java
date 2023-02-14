package filters;

import java.io.EOFException;
import java.util.ArrayList;

/**
 * The NoiseWordRemoval filter
 */
public class NoiseWordRemoval extends Filter {

    private final ArrayList<String> noiseWords;

    /**
     * Reads from its pipe, removes noise words, and writes to its pipe
     */
    public NoiseWordRemoval() {
        Settings settings = Settings.getInstance();
        noiseWords = settings.getStopWords();
    }

    /**
     * Checks if a word is a noise word
     *
     * @param word The word to check
     * @return True if the word is a noise word, false otherwise
     */
    private boolean isNoiseWord(String word) {
        return noiseWords.contains(word);
    }

    /**
     * Reads from its pipe, removes noise words, and writes to its pipe
     */
    public void run() {
        String linesString;
        try {
            linesString = read();
        } catch (EOFException e) {
            // TODO: Auto-generated catch block
            e.printStackTrace();
            return;
        }
        String[] lines = linesString.split("\\r?\\n");
        ArrayList<String> filteredLines = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split(" ");
            StringBuilder filteredLineBuilder = new StringBuilder();
            for (String word : words) {
                if (!isNoiseWord(word)) {
                    filteredLineBuilder.append(word).append(" ");
                }
            }
            String filteredLine = filteredLineBuilder.toString().trim();
            if (!filteredLine.isEmpty() && !filteredLines.contains(filteredLine)) {
                filteredLines.add(filteredLine);
            }
        }
        write(String.join("\r\n", filteredLines));
    }
}
