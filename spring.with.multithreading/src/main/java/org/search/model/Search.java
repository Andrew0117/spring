package org.search.model;

public class Search {

    private String search;

    public Search() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "Search{" +
                "search='" + search + '\'' +
                '}';
    }

}
