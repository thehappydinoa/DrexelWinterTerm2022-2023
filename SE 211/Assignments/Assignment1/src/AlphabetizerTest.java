import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetizerTest {

    @Test
    void sort() {
        LineStorage lineStorage = new LineStorage();
        lineStorage.addLine("test");
        lineStorage.addLine("abc");
        lineStorage.addLine("xyz");
        Alphabetizer.sort(lineStorage);
        ArrayList<String> lines = lineStorage.getLines();
        assertEquals("abc", lines.get(0));
        assertEquals("test", lines.get(1));
        assertEquals("xyz", lines.get(2));
    }
}