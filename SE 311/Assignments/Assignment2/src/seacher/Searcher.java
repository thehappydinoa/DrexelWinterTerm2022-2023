package seacher;

import filters.Filter;
import utilities.Settings;

import java.io.EOFException;
import java.util.ArrayList;

public class Searcher extends Filter {
    private final String keyword;

    /**
     * Creates a new searcher filter
     */
    public Searcher(String keyword) {
        this.keyword = keyword;
    }

    public Searcher() {
        this(Settings.getInstance().getKeyword());
    }

    /**
     * Reads from its pipe, searches for the keyword, and writes to its pipe
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
        StringBuilder filteredLinesBuilder = new StringBuilder();
        for (String line : lines) {
            if (line.contains(keyword)) {
                filteredLinesBuilder.append(line).append("\r\n");
            }
        }
        write(filteredLinesBuilder.toString());
    }
}
