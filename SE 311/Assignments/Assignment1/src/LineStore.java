import java.util.ArrayList;

public class LineStore {
    private ArrayList<String> lines = new ArrayList<String>();
    private int currentLine = 0;

    public void addLine(String line) {
        lines.add(line);
    }

    public String getLine(int index) {
        return lines.get(index);
    }

    public int getLineCount() {
        return lines.size();
    }

    public String getNextLine() {
        if (currentLine < lines.size()) {
            return lines.get(currentLine++);
        } else {
            return null;
        }
    }

    public void reset() {
        currentLine = 0;
    }
}
