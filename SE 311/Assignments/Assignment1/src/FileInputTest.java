import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileInputTest {
    private static final String FILENAME = "test.txt";

    @Test
    void readlinesTest() throws FileNotFoundException {
        // Given
        String[] expectedLines = new String[] {"line1", "line2", "line3"};
        File file = createTestFile(FILENAME, expectedLines);
        FileInput input = new FileInput(FILENAME);

        // When
        ArrayList<String> lines = input.readlines();

        // Then
        assertNotNull(lines);
        assertArrayEquals(expectedLines, lines.toArray());
        assertTrue(file.delete());
    }

    @Test
    void readTest() throws FileNotFoundException {
        // Given
        String[] expectedLines = new String[] {"line1", "line2", "line3"};
        File file = createTestFile(FILENAME, expectedLines);
        FileInput input = new FileInput(FILENAME);

        // When
        KwicIndex kwicIndex = input.read();

        // Then
        assertNotNull(kwicIndex);
        assertArrayEquals(expectedLines, kwicIndex.getSentences().toArray());
        assertTrue(file.delete());
    }

    private File createTestFile(String filename, String[] lines) {
        File file = new File(filename);
        file.deleteOnExit();
        try (PrintWriter writer = new PrintWriter(file)) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            // Handle error
        }
        return file;
    }

}
