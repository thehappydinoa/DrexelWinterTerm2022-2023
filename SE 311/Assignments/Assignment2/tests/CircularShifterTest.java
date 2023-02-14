import circularshifter.CircularShifter;
import filters.Pipe;
import org.junit.jupiter.api.Test;

import java.io.EOFException;

import static org.junit.jupiter.api.Assertions.*;

class CircularShifterTest {

    @Test
    void testCircularShifter() throws EOFException {
        String input = "Sense and Sensibility\r\nArchitecture Software\r\nCrouching Tiger Hidden Dragon";
        String expected = "Sense and Sensibility\r\nArchitecture Software\r\nCrouching Tiger Hidden Dragon\r\nand Sensibility Sense\r\nSensibility Sense and\r\nSoftware Architecture\r\nTiger Hidden Dragon Crouching\r\nHidden Dragon Crouching Tiger\r\nDragon Crouching Tiger Hidden";
        CircularShifter circularShifter = new CircularShifter();
        Pipe in = new Pipe();
        in.write(input);
        circularShifter.setIn(in);
        Pipe out = new Pipe();
        circularShifter.setOut(out);
        circularShifter.run();
        String actual = out.read();
        assertEquals(expected, actual);
    }
}