import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchResult {
    private final String searchKeyword;
    private final ArrayList<String> sentences;
    private final int size;

    public SearchResult(String searchKeyword, ArrayList<String> sentences, int size) {
        this.searchKeyword = searchKeyword;
        this.sentences = sentences;
        this.size = size;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
    public ArrayList<String> getSentences() {
        return sentences;
    }
    public int getSize() {
        return size;
    }

    public String toString(boolean highlight) {
        if (!highlight) {
            return String.join("\r\n", sentences) + "\r\n";
        }

        String searchKeywordEscaped = Pattern.quote(searchKeyword);
        String regex = "(?i)" + searchKeywordEscaped;
        String replacement = "\033[1;31m$0\033[0m";

        List<String> highlightedSentences = sentences.stream()
                .map(sentence -> sentence.replaceAll(regex, replacement))
                .collect(Collectors.toList());

        return String.join("\r\n", highlightedSentences) + "\r\n";
    }
    public String toString() {
        return toString(false);
    }
}
