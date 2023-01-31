import java.util.ArrayList;

public class SearchResult {
    private final String searchKeyword;
    private final ArrayList<String> sentences;
    private final int size;

    public SearchResult(String searchKeyword, ArrayList<String> sentences, int size) {
        this.searchKeyword = searchKeyword;
        this.sentences = sentences;
        this.size = size;
    }
    public SearchResult(String searchKeyword, ArrayList<String> sentences, KwicIndex kwicIndex) {
        this(searchKeyword, sentences, kwicIndex.getNumSentences());
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
        StringBuilder sb = new StringBuilder();
        for (String sentence : sentences) {
            if (highlight) {
                if (sentence.contains(searchKeyword)) {
                    sb.append(sentence.replace(searchKeyword, "\033[1;31m" + searchKeyword + "\033[0m"));
                } else if (sentence.contains(searchKeyword.toLowerCase())) {
                    sb.append(sentence.replace(searchKeyword.toLowerCase(), "\033[1;31m" + searchKeyword.toLowerCase() + "\033[0m"));
                } else if (sentence.contains(searchKeyword.toUpperCase())) {
                    sb.append(sentence.replace(searchKeyword.toUpperCase(), "\033[1;31m" + searchKeyword.toUpperCase() + "\033[0m"));
                }
            } else {
                sb.append(sentence);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public String toString() {
        return toString(false);
    }
}
