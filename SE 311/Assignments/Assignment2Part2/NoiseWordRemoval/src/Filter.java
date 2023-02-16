import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// This is the updated filter for part 2
public abstract class Filter implements Runnable {

    public void write(String s) {
        System.out.print(s);
    }

    public String read() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
			StringBuilder response = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                response.append(line).append("\r\n");
            }
            return response.toString();
        } catch (IOException e) {
            System.err.println("IOException caught in Filter::StandardIn");
            return "IOException caught in Filter::StandardIn";
        }
    }
}
