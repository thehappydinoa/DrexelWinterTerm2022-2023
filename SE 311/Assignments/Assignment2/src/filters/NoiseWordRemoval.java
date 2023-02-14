package filters;

import java.io.EOFException;
import java.util.ArrayList;

public class NoiseWordRemoval extends Filter {

    private Settings settings;
    private ArrayList<String> noiseWords;

    public NoiseWordRemoval() {
        settings = Settings.getInstance();
        noiseWords = settings.getStopWords();
    }

    private boolean isNoiseWord(String word) {
        return noiseWords.contains(word);
    }

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
