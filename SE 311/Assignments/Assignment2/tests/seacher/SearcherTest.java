package seacher;

import filters.Pipe;
import org.junit.jupiter.api.Test;

import java.io.EOFException;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {
    @Test
    void testRun() throws EOFException {
        String input = "foo\r\nbar\r\nbaz\r\nqux\r\n";
        String expected = "bar\r\nbaz\r\n";
        Searcher searcher = new Searcher("ba");
        Pipe in = new Pipe();
        in.write(input);
        Pipe out = new Pipe();
        searcher.setIn(in);
        searcher.setOut(out);
        searcher.run();
        String actual = out.read();
        assertEquals(expected, actual);
    }

}