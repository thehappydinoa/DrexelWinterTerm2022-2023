package filters;

import java.io.EOFException;
import java.util.Arrays;

public class Alphabetizer extends Filter {
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
    Arrays.sort(lines, String.CASE_INSENSITIVE_ORDER);
    write(String.join("\r\n", lines));
  }
}
