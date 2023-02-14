package filters;

import java.io.EOFException;
import java.util.ArrayList;

public class CircularShifter extends Filter {
  public void run() {
    String linesString;
    try {
      linesString = read();
    } catch (EOFException e) {
      // TODO: Auto-generated catch block
      e.printStackTrace();
      return;
    }
    String[] lines = linesString.split("\\r?\\n");
    ArrayList<String> shiftedLines = new ArrayList<>();
    for (String line : lines) {
      String[] words = line.split(" ");
      for (int i = 0; i < words.length; i++) {
        // Shift the words
        StringBuilder shiftedLineBuilder = new StringBuilder();
        for (int k = 0; k < words.length; k++) {
          shiftedLineBuilder.append(words[(i + k) % words.length]).append(" ");
        }
        String shiftedLine = shiftedLineBuilder.toString().trim();
        // Add the shifted line to the list
        if (!shiftedLine.equals(line)) {
          shiftedLines.add(shiftedLine);
        }
      }
    }
    write(String.join("\r\n", shiftedLines));
  }

}
