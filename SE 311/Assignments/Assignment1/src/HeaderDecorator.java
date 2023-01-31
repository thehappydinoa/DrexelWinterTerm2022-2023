class HeaderDecorator extends SearchResult {
    private final SearchResult searchResult;

    public HeaderDecorator(SearchResult searchResult) {
        super(searchResult.getSearchKeyword(), searchResult.getSentences(), searchResult.getSize());
        this.searchResult = searchResult;
    }

    @Override
    public String toString(boolean highlight) {
        // Display "[X] results are found in [Y] records"
        return "--- Header ---\n" + searchResult.getSize() + " results are found in " + searchResult.getSentences().size() + " records\n" + searchResult.toString(highlight);
    }
}