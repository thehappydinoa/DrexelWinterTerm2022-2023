class HeaderDecorator extends SearchResult {
    private final SearchResult searchResult;

    public HeaderDecorator(SearchResult searchResult) {
        super(searchResult.getSearchKeyword(), searchResult.getSentences(), searchResult.getSize());
        this.searchResult = searchResult;
    }

    @Override
    public String toString(boolean highlight) {
        // Display "[X] results are found in [Y] records"
        return searchResult.getSentences().size() + " results are found in " + searchResult.getSize() + " records\n" + searchResult.toString(highlight);
    }
}