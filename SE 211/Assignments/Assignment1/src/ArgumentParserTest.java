import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentParserTest {

    @Test
    void testParse() {
        String[] args = {"test.txt"};
        ArgumentParser parser = new ArgumentParser(args);
        assertEquals("test.txt", parser.getInputFileName());
        assertFalse(parser.hasStopWordsFileName());
    }

    @Test
    void testParseWithStopWordsFile() {
        String[] args = {"-s", "stop_words.txt", "test_1.txt"};
        ArgumentParser parser = new ArgumentParser(args);
        assertEquals("test_1.txt", parser.getInputFileName());
        assertTrue(parser.hasStopWordsFileName());
        assertEquals("stop_words.txt", parser.getStopWordsFileName());
    }

    @Test
    void testParseWithInvalidArgs() {
        String[] args = {"-s", "stop_words.txt"};
        assertThrows(IllegalArgumentException.class, () -> new ArgumentParser(args));
    }
}