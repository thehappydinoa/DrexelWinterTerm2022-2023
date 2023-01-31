import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KwicIndexTest {

    @Test
    void initKwicIndexWithSentencesAndStopWords() {
        // Given
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("This is a sentence.");
        ArrayList<String> stopWords = new ArrayList<>();
        stopWords.add("is");

        // When
        KwicIndex kwic = new KwicIndex(sentences, stopWords);

        // Then
        assertEquals(1, kwic.getNumSentences());
        assertEquals("This is a sentence.", kwic.getSentences().get(0));
        assertEquals(1, kwic.getNumStopWords());
        assertEquals("is", kwic.getStopWords().get(0));
        assertTrue(kwic.isStopWord("is"));
    }

    @Test
    void initKwicIndexWithSentences() {
        // Given
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("This is a sentence.");

        // When
        KwicIndex kwic = new KwicIndex(sentences);

        // Then
        assertEquals(1, kwic.getNumSentences());
        assertEquals("This is a sentence.", kwic.getSentences().get(0));
        assertEquals(0, kwic.getNumStopWords());
        assertFalse(kwic.isStopWord("is"));
    }

    @Test
    void initKwicIndexWithNoArguments() {
        // When
        KwicIndex kwic = new KwicIndex();

        // Then
        assertEquals(0, kwic.getNumSentences());
        assertEquals(0, kwic.getNumStopWords());
    }

//    @Test
//    void initKwicIndexFromFile() throws FileNotFoundException {
//        // When
//        KwicIndex kwic = KwicIndex.fromFile("filename");
//
//        // Then
//        assertEquals(0, kwic.getNumSentences());
//        assertEquals(0, kwic.getNumStopWords());
//    }

    @Test
    void addSentence() {
        // Given
        KwicIndex kwic = new KwicIndex();

        // When
        kwic.addSentence("This is a sentence.");

        // Then
        assertArrayEquals(new String[] {"This is a sentence."}, kwic.getSentences().toArray());
    }

    @Test
    void addSentences() {
        // Given
        ArrayList<String> original = new ArrayList<>();
        original.add("This is the first sentence.");
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("This is a sentence.");
        sentences.add("This is another sentence.");
        KwicIndex kwic = new KwicIndex(original);

        // When
        kwic.addSentences(sentences);

        // Then
        assertArrayEquals(new String[] {
                "This is the first sentence.",
                "This is a sentence.",
                "This is another sentence."
        }, kwic.getSentences().toArray());
    }

    @Test
    void setSentences() {
        // Given
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("This is a sentence.");
        sentences.add("This is another sentence.");
        KwicIndex kwic = new KwicIndex();

        // When
        kwic.setSentences(sentences);

        // Then
        assertArrayEquals(new String[] {
                "This is a sentence.",
                "This is another sentence."
        }, kwic.getSentences().toArray());
    }

    @Test
    void getSentences() {
        // Given
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("This is a sentence.");
        sentences.add("This is another sentence.");
        KwicIndex kwic = new KwicIndex(sentences);

        // When
        ArrayList<String> result = kwic.getSentences();

        // Then
        assertArrayEquals(new String[] {
                "This is a sentence.",
                "This is another sentence."
        }, result.toArray());
    }

    @Test
    void getNumSentences() {
        // Given
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("This is a sentence.");
        sentences.add("This is another sentence.");
        KwicIndex kwic = new KwicIndex(sentences);

        // When
        int result = kwic.getNumSentences();

        // Then
        assertEquals(2, result);
    }

    @Test
    void addStopWord() {
        // Given
        KwicIndex kwic = new KwicIndex();

        // When
        kwic.addStopWord("is");

        // Then
        assertArrayEquals(new String[] {"is"}, kwic.getStopWords().toArray());
    }

    @Test
    void addStopWords() {
        // Given
        ArrayList<String> original = new ArrayList<>();
        original.add("is");
        ArrayList<String> stopWords = new ArrayList<>();
        stopWords.add("a");
        stopWords.add("the");
        KwicIndex kwic = new KwicIndex();
        kwic.setStopWords(original);

        // When
        kwic.addStopWords(stopWords);

        // Then
        assertArrayEquals(new String[] {
                "is",
                "a",
                "the"
        }, kwic.getStopWords().toArray());
    }

    @Test
    void setStopWords() {
        // Given
        ArrayList<String> stopWords = new ArrayList<>();
        stopWords.add("a");
        stopWords.add("the");
        KwicIndex kwic = new KwicIndex();

        // When
        kwic.setStopWords(stopWords);

        // Then
        assertArrayEquals(new String[] {
                "a",
                "the"
        }, kwic.getStopWords().toArray());
    }

    @Test
    void getStopWords() {
        // Given
        ArrayList<String> stopWords = new ArrayList<>();
        stopWords.add("a");
        stopWords.add("the");
        KwicIndex kwic = new KwicIndex();
        kwic.setStopWords(stopWords);

        // When
        ArrayList<String> result = kwic.getStopWords();

        // Then
        assertArrayEquals(new String[] {
                "a",
                "the"
        }, result.toArray());
    }

    @Test
    void getNumStopWords() {
        // Given
        ArrayList<String> stopWords = new ArrayList<>();
        stopWords.add("a");
        stopWords.add("the");
        KwicIndex kwic = new KwicIndex();
        kwic.setStopWords(stopWords);

        // When
        int result = kwic.getNumStopWords();

        // Then
        assertEquals(2, result);
    }

    @Test
    void isStopWord() {
        // Given
        ArrayList<String> stopWords = new ArrayList<>();
        stopWords.add("a");
        stopWords.add("the");
        KwicIndex kwic = new KwicIndex();
        kwic.setStopWords(stopWords);

        // When
        boolean result = kwic.isStopWord("a");

        // Then
        assertTrue(result);
    }

    @Test
    void isNotStopWord() {
        // Given
        ArrayList<String> stopWords = new ArrayList<>();
        stopWords.add("a");
        stopWords.add("the");
        KwicIndex kwic = new KwicIndex(stopWords);

        // When
        boolean result = kwic.isStopWord("is");

        // Then
        assertFalse(result);
    }

    @Test
    void save() {
        // Given
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("This is a sentence.");
        sentences.add("This is another sentence.");
        ArrayList<String> stopWords = new ArrayList<>();
        stopWords.add("a");
        stopWords.add("the");
        KwicIndex kwic = new KwicIndex(sentences, stopWords);
        URL rootResource = getClass().getResource("/");
        if (rootResource == null) {
            fail("Could not find root resource.");
        }
        String expectedPath = rootResource.getPath() + "indexes/filename.index";
        File expected = new File(expectedPath);
        assertFalse(expected.exists());

        // When
        kwic.save("filename.index");

        // Then
        assertTrue(expected.exists());
        assertTrue(expected.delete());
    }
}