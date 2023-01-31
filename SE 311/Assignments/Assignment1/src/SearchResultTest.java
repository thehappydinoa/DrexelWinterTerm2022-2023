import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class SearchResultTest {
    @Test
    public void constructor() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("keyword 1");
        sentences.add("keyword 2");
        SearchResult searchResult = new SearchResult("keyword", sentences, 2);
        assertEquals("keyword", searchResult.getSearchKeyword());
        assertEquals(sentences, searchResult.getSentences());
        assertEquals(2, searchResult.getSize());
    }

    @Test
    public void constructorWithKwicIndex() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("keyword 1");
        sentences.add("keyword 2");
        KwicIndex kwicIndex = new KwicIndex(sentences);
        SearchResult searchResult = new SearchResult("keyword", sentences, kwicIndex);
        assertEquals("keyword", searchResult.getSearchKeyword());
        assertEquals(sentences, searchResult.getSentences());
        assertEquals(2, searchResult.getSize());
    }

    @Test
    public void toStringWithHighlight() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("sentence with keyword");
        SearchResult searchResult = new SearchResult("keyword", sentences, 1);
        assertEquals("sentence with \033[1;31mkeyword\033[0m\n", searchResult.toString(true));
    }

    @Test
    public void toStringWithoutHighlight() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("sentence with keyword");
        SearchResult searchResult = new SearchResult("keyword", sentences, 1);
        assertEquals("sentence with keyword\n", searchResult.toString());
    }
}
