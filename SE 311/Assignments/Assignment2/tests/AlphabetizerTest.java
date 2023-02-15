import alphabetizer.Alphabetizer;
import filters.Pipe;
import org.junit.jupiter.api.Test;

import java.io.EOFException;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetizerTest {
    @Test
    void testAlphabetizer() throws EOFException {
        String input = "a\r\nof\r\nthe\r\nThis\r\nalphabetizer\r\nfilter\r\ntest";
        String expected = "a\r\nalphabetizer\r\nfilter\r\nof\r\ntest\r\nthe\r\nThis";
        Alphabetizer alphabetizer = new Alphabetizer();
        Pipe in = new Pipe();
        in.write(input);
        alphabetizer.setIn(in);
        Pipe out = new Pipe();
        alphabetizer.setOut(out);
        alphabetizer.run();
        String actual = out.read();
        assertEquals(expected, actual);
    }

    @Test
    void testAlphabetizerNewlinesOnly() throws EOFException {
        String input = "a\nof\nthe\nThis\nalphabetizer\nfilter\ntest";
        String expected = "a\r\nalphabetizer\r\nfilter\r\nof\r\ntest\r\nthe\r\nThis";
        Alphabetizer alphabetizer = new Alphabetizer();
        Pipe in = new Pipe();
        in.write(input);
        alphabetizer.setIn(in);
        Pipe out = new Pipe();
        alphabetizer.setOut(out);
        alphabetizer.run();
        String actual = out.read();
        assertEquals(expected, actual);
    }

}