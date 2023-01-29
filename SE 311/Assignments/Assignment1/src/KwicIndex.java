import java.io.FileNotFoundException;
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

    public void addStopWord(String stopWord) {
        stopWords.add(stopWord);
    }

    public void addStopWords(ArrayList<String> stopWord) {
        this.stopWords.addAll(stopWord);
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }

    public ArrayList<String> getStopWords() {
        return stopWords;
    }

    public Boolean isStopWord(String word) {
        return stopWords.contains(word);
    }

    public void save(String filename) {
        FileOutput fo = new FileOutput(filename);
        fo.write(sentences);
    }
}
