import java.io.FileNotFoundException;

public class SearchIndex {
    public static void main(String[] args) {
        // Read settings
        Settings settings = new Settings();

        // Open index
        String indexFilename = settings.getIndexFilename();
        KwicIndex kwicIndex = null;
        try {
            kwicIndex = KwicIndex.fromFile(indexFilename);
        } catch (FileNotFoundException e) {
            ConsoleOutput.writeError("Could not find index file " + indexFilename);
            System.exit(1);
        }

        // Get search term
        ConsoleInput consoleInput = new ConsoleInput();
        String searchTerm = consoleInput.readSearchTerm();

        // Search index
        CaseInsensitiveSearch caseInsensitiveSearch = new CaseInsensitiveSearch();
        SearchResult searchResult = caseInsensitiveSearch.search(kwicIndex, searchTerm);

        // Should we add a header?
        if (settings.shouldAddHeader()) {
            searchResult = new HeaderDecorator(searchResult);
        }

        // Should we add a footer?
        if (settings.shouldAddFooter()) {
            searchResult = new FooterDecorator(searchResult);
        }

        // Write output
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.write(searchResult.toString(true));
    }
}
