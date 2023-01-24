import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputTest {

    @Test
    void constructorFail() {
        assertThrows(FileNotFoundException.class, () -> new Input("this_file_does_not_exist.txt"));
    }

    @Test
    void addLines() throws FileNotFoundException {
        LineStorage lineStorage = new LineStorage();
        Input input = new Input("input.txt");
        lineStorage.addLines(input.readlines());
        assertArrayEquals(new String[]{
                "Sense and Sensibility",
                "Architecture Software",
                "Crouching Tiger Hidden Dragon"
        }, lineStorage.getLines().toArray());
    }
}