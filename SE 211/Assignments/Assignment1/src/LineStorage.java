import java.util.ArrayList;

public class LineStorage {
    private ArrayList<String> lines;
    public LineStorage() {
        lines = new ArrayList<String>();
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }
}
