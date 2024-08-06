package org.search.entities.response;

public class ResponseGoogleSearch {

    private SearchInformation searchInformation;

    public ResponseGoogleSearch() {
    }

    public SearchInformation getSearchInformation() {
        return searchInformation;
    }

    public void setSearchInformation(SearchInformation searchInformation) {
        this.searchInformation = searchInformation;
    }

    @Override
    public String toString() {
        return "ResponseGoogleSearch{" +
                "searchInformation=" + searchInformation +
                '}';
    }
}
