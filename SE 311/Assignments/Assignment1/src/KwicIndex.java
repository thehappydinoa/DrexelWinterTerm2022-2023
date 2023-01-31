import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;

public class KwicIndex {
    // Attributes
    private ArrayList<String> sentences;
    private ArrayList<String> stopWords;

    // Constructors
    public KwicIndex(ArrayList<String> sentences, ArrayList<String> stopWords) {
        this.sentences = sentences;
        this.stopWords = stopWords;
    }
    public KwicIndex(ArrayList<String> sentences) {
        this(sentences, new ArrayList<>());
    }
    KwicIndex() {
        this(new ArrayList<>(), new ArrayList<>());
    }
    public static KwicIndex fromFile(String filename) throws FileNotFoundException {
        KwicIndex kwic = new KwicIndex();
        kwic.addSentences(new FileInput(filename).readlines());
        return kwic;
    }

    // Methods
    public void addSentence(String sentence) {
        sentences.add(sentence);
    }

    public void addSentences(ArrayList<String> sentences) {
        this.sentences.addAll(sentences);
    }

    public void setSentences(ArrayList<String> newSentences) {
        sentences = newSentences;
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }

    public int getNumSentences() {
        return sentences.size();
    }

    public void addStopWord(String stopWord) {
        stopWords.add(stopWord);
    }

    public void addStopWords(ArrayList<String> stopWord) {
        this.stopWords.addAll(stopWord);
    }

    public void setStopWords(ArrayList<String> newStopWords) {
        stopWords = newStopWords;
    }

    public ArrayList<String> getStopWords() {
        return stopWords;
    }

    public int getNumStopWords() {
        return stopWords.size();
    }

    public Boolean isStopWord(String word) {
        return stopWords.contains(word);
    }

    public void save(String filename) {
        // Get resources/indexes directory
        URL rootResource = getClass().getResource("/");
        if (rootResource == null) {
            System.out.println("Error: Could not find root resource directory");
            return;
        }
        String indexesDir = rootResource.getPath() + "indexes/";

        // If the directory doesn't exist, create it
        FileOutput.createDir(indexesDir);

        // Get the full path to the index file
        String indexDir = indexesDir + filename;

        // Write the sentences to the file
        FileOutput fo = new FileOutput(indexDir);
        fo.write(sentences);
    }
}
