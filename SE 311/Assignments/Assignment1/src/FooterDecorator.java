import java.text.SimpleDateFormat;
import java.util.Date;

class FooterDecorator extends SearchResult {
    private final SearchResult searchResult;

    public FooterDecorator(SearchResult searchResult) {
        super(searchResult.getSearchKeyword(), searchResult.getSentences(), searchResult.getSize());
        this.searchResult = searchResult;
    }

    @Override
    public String toString(boolean highlight) {
        // Display time and date of the search
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return searchResult.toString(highlight) + "--- Footer ---\n" + date;
    }
}