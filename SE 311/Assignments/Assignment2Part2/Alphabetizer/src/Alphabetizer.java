import java.io.EOFException;
import java.util.Arrays;

/**
 * The Alphabetizer filter
 */
public class Alphabetizer extends Filter {

    /**
     * Reads from its pipe, sorts the lines, and writes to its pipe
     */
    public void run() {
        String linesString = read();
        String[] lines = linesString.split("\\r?\\n");
        Arrays.sort(lines, String.CASE_INSENSITIVE_ORDER);
        write(String.join("\r\n", lines) + "\r\n");
    }
}