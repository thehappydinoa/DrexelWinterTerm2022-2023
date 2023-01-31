import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HeaderDecoratorTest {
    private SearchResult searchResult;
    @BeforeEach
    public void setUp() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("sentence with keyword");
        searchResult = new SearchResult("keyword", sentences, 1);
    }

    @Test
    public void testToStringWithHighlight() {
        // When
        HeaderDecorator headerDecorator = new HeaderDecorator(searchResult);

        // Then
        assertEquals("1 results are found in 1 records\nsentence with \033[1;31mkeyword\033[0m\n", headerDecorator.toString(true));
    }

    @Test
    public void testToStringWithoutHighlight() {
        // When
        HeaderDecorator headerDecorator = new HeaderDecorator(searchResult);

        // Then
        assertEquals("1 results are found in 1 records\nsentence with keyword\n", headerDecorator.toString());
    }
}