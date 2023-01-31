import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AlphabeticalSorterTest {

    @Test
    void sort() {
        // Given
        ArrayList<String> lines = new ArrayList<>();
        lines.add("b");
        lines.add("a");
        lines.add("c");
        KwicIndex kwicIndex = new KwicIndex(lines);
        AlphabeticalSorter alphabeticalSorter = new AlphabeticalSorter();

        // When
        alphabeticalSorter.sort(kwicIndex);

        // Then
        assertArrayEquals(new String[] {
                "a",
                "b",
                "c"
        }, kwicIndex.getSentences().toArray());
    }
}