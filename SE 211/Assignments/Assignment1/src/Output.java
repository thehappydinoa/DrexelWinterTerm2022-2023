import java.util.ArrayList;

public class Output {
    public static void print(String text) {
        System.out.println(text);
    }

    public static void printLines(LineStorage lineStorage) {
        ArrayList<String> lines = lineStorage.getLines();
        for (String line : lines) {
            print(line);
        }
    }

    public static void error(String text) {
        print("\nError: " + text);
        System.exit(1);
    }

    public static void warn(String text) {
        print("Warning: " + text);
    }
}
