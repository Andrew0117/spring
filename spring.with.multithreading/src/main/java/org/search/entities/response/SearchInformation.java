package org.search.entities.response;

public class SearchInformation {

    private Long totalResults;

    public SearchInformation() {
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "SearchInformation{" +
                "totalResults=" + totalResults +
                '}';
    }
}
