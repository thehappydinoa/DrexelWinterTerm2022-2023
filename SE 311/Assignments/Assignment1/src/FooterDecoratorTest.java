import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FooterDecoratorTest {
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
        FooterDecorator footerDecorator = new FooterDecorator(searchResult);

        // Then
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        assertEquals("sentence with \033[1;31mkeyword\033[0m\n--- Footer ---\n" + date, footerDecorator.toString(true));
    }

    @Test
    public void testToStringWithoutHighlight() {
        // When
        FooterDecorator footerDecorator = new FooterDecorator(searchResult);

        // Then
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        assertEquals("sentence with keyword\n--- Footer ---\n" + date, footerDecorator.toString());
    }
}
