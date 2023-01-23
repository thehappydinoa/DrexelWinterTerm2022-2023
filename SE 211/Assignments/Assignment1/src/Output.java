import java.util.ArrayList;

public class Output {
    private LineStorage lineStorage;
    public Output(LineStorage lineStorage) {
        this.lineStorage = lineStorage;
    }

    public void out() {
        ArrayList<String> lines = lineStorage.getLines();
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
