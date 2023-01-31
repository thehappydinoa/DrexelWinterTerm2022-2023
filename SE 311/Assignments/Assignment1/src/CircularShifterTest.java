import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CircularShifterTest {

    @Test
    void shift() {
        // Given
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Sense and Sensibility");
        lines.add("Architecture Software");
        lines.add("Crouching Tiger Hidden Dragon");
        KwicIndex kwicIndex = new KwicIndex(lines);
        CircularShifter circularShifter = new CircularShifter();

        // When
        circularShifter.shift(kwicIndex);

        // Then
        ArrayList<String> sentences = kwicIndex.getSentences();
        assertNotNull(sentences);
        assertArrayEquals(new String[] {
                "Sense and Sensibility",
                "Architecture Software",
                "Crouching Tiger Hidden Dragon",
                "and Sensibility Sense",
                "Sensibility Sense and",
                "Software Architecture",
                "Tiger Hidden Dragon Crouching",
                "Hidden Dragon Crouching Tiger",
                "Dragon Crouching Tiger Hidden"
        }, sentences.toArray());
    }
}