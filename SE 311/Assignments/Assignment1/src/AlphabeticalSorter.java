import java.util.ArrayList;

public class AlphabeticalSorter extends Sorter {
    @Override
    void sort(KwicIndex kwicIndex) {
        ArrayList<String> lines = kwicIndex.getSentences();
        lines.sort(String::compareToIgnoreCase);
        kwicIndex.setSentences(lines);
    }
}

