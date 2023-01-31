import java.util.ArrayList;

public class ConsoleOutput extends Output {
    public static final String OUTPUT_TYPE = "console";
    public void write(String s) {
        System.out.println(s);
    }

    public void write(ArrayList<String> s) {
        for (String line : s) {
            write(line);
        }
    }

    public static void writeError(String s) {
        System.err.println("Error: " + s);
    }

    public static void writeWarning(String s) {
        System.err.println("Warning: " + s);
    }
}
