import java.util.ArrayList;

public class Searcher extends Filter {
    private final Settings settings;
    private final String keyword;

    /**
     * Creates a new searcher filter
     */
    public Searcher(Settings settings, String keyword) {
        this.settings = settings;
        this.keyword = keyword;
    }

    public Searcher() {
        this(Settings.getInstance(), Settings.getInstance().getKeyword());
    }

    /**
     * Reads from its pipe, searches for the keyword, and writes to its pipe
     */
    public void run() {
//        String linesString = read();
//        String[] lines = linesString.split("\\r?\\n");
        String[] lines = {
            "Architecture Software",
            "Crouching Tiger Hidden Dragon",
            "Dragon Crouching Tiger Hidden",
            "Hidden Dragon Crouching Tiger",
            "Sense Sensibility",
            "Sensibility Sense",
            "Software Architecture",
            "Tiger Hidden Dragon Crouching",
        };
        ArrayList<String> filteredLines = new ArrayList<>();
        for (String line : lines) {
            if (line.toLowerCase().contains(keyword.toLowerCase())) {
                filteredLines.add(line);
            }
        }
        SearchResult result = new SearchResult(keyword, filteredLines, lines.length);
        if (settings.shouldAddHeader()) {
            result = new HeaderDecorator(result);
        }
        if (settings.shouldAddFooter()) {
            result = new FooterDecorator(result);
        }
        write(result.toString(true) + "\r\n");
    }
}
