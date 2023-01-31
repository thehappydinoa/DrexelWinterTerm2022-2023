import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleInput extends Input {
    public static final String INPUT_TYPE = "console";

    public ArrayList<String> readlines() throws IOException {
        // Create a new ArrayList to store the lines
        ArrayList<String> lines = new ArrayList<>();
        // Create a new BufferedReader to read from the console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Create a new String to store the current line
        String line;
        // While the current line is not null
        while ((line = br.readLine()) != null) {
            // Add the current line to the ArrayList
            lines.add(line);
        }
        // Return the ArrayList as an array
        return lines;
    }

    public KwicIndex read() throws IOException {
        // Read lines from the console and return a KwicIndex
        return new KwicIndex(readlines());
    }

    public String readSearchTerm() {
        // Prompt the user for a search term
        System.out.print("Search term: ");
        // Create a new BufferedReader to read from the console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Create a new String to store the search term
        String searchTerm;
        // While the search term is null
        while (true) {
            try {
                // Read the search term from the console
                searchTerm = br.readLine();
                // If the search term is not null
                if (searchTerm != null) {
                    // Return the search term
                    return searchTerm;
                }
            } catch (IOException e) {
                // Do nothing
            }
        }
    }
}
