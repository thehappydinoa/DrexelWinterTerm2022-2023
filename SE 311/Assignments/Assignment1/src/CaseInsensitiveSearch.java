import java.util.ArrayList;

public class CaseInsensitiveSearch extends SearchAlgorithm {
    @Override
    public SearchResult search(KwicIndex kwicIndex, String query) {
        ArrayList<String> lines = kwicIndex.getSentences();
        ArrayList<String> results = new ArrayList<>();
        for (String line : lines) {
            if (line.toLowerCase().startsWith(query.toLowerCase()))
                results.add(line);
        }
        return new SearchResult(query, results, kwicIndex);
    }
}
