import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CircularShiftTest {

    @Test
    void shift() {
        LineStorage lineStorage = new LineStorage();
        lineStorage.addLine("Sense and Sensibility");
        lineStorage.addLine("Architecture Software");
        lineStorage.addLine("Crouching Tiger Hidden Dragon");
        CircularShift.shift(lineStorage);
        ArrayList<String> lines = lineStorage.getLines();
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
        }, lines.toArray());
    }
}