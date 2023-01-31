import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputTest {

    @Test
    void readlines() throws IOException {
        // Given
        ConsoleInput input = new ConsoleInput();
        InputStream original = System.in;
        InputStream testInput = new ByteArrayInputStream("line1\nline2\nline3\n".getBytes());
        System.setIn(testInput);

        // When
        ArrayList<String> lines = input.readlines();

        // Then
        assertNotNull(lines);
        assertEquals(3, lines.size());
        assertEquals("line1", lines.get(0));
        assertEquals("line2", lines.get(1));
        assertEquals("line3", lines.get(2));

        // Reset System.in to its original value
        System.setIn(original);
    }

    @Test
    void read() throws IOException {
        // Given
        ConsoleInput input = new ConsoleInput();
        InputStream original = System.in;
        InputStream testInput = new ByteArrayInputStream("line1\nline2\nline3\n".getBytes());
        System.setIn(testInput);

        // When
        KwicIndex kwicIndex = input.read();

        // Then
        assertNotNull(kwicIndex);
        ArrayList<String> sentences = kwicIndex.getSentences();
        assertNotNull(sentences);
        assertArrayEquals(new String[] {
                "line1",
                "line2",
                "line3"
        }, sentences.toArray());

        // Reset System.in to its original value
        System.setIn(original);
    }
}
