import java.util.ArrayList;

public class LineStorage {
    private ArrayList<String> lines;
    private ArrayList<String> stopWords;
    public LineStorage(ArrayList<String> lines, ArrayList<String> stopWords) {
        setLines(lines);
        setStopWords(stopWords);
    }

    public LineStorage(ArrayList<String> lines) {
        setLines(lines);
        setStopWords(new ArrayList<>());
    }

    public LineStorage() {
        setLines(new ArrayList<>());
        setStopWords(new ArrayList<>());
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void addLines(ArrayList<String> newLines) {
        lines.addAll(newLines);
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> newLines) {
        lines = newLines;
    }

    public void addStopWord(String stopWord) {
        stopWords.add(stopWord);
    }

    public void addStopWords(ArrayList<String> newStopWords) {
        stopWords.addAll(newStopWords);
    }

    public ArrayList<String> getStopWords() {
        return stopWords;
    }

    public void setStopWords(ArrayList<String> newStopWords) {
        // Make sure all stop words are lowercase
        newStopWords.replaceAll(s -> s.toLowerCase().trim());
        stopWords = newStopWords;
    }

    public boolean isStopWord(String word) {
        return stopWords.contains(word.toLowerCase().trim());
    }
}
