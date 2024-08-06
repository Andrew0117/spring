package org.search.entities;

import org.search.entities.base.BaseEntity;

import java.util.Objects;

public class GoogleEntity extends BaseEntity {

    private String api_key;

    private String search_engine_id;

    public GoogleEntity(String baseUrl, String api_key, String search_engine_id) {
        super(baseUrl);
        this.api_key = api_key;
        this.search_engine_id = search_engine_id;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getSearch_engine_id() {
        return search_engine_id;
    }

    public void setSearch_engine_id(String search_engine_id) {
        this.search_engine_id = search_engine_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GoogleEntity that = (GoogleEntity) o;
        return Objects.equals(api_key, that.api_key) && Objects.equals(search_engine_id, that.search_engine_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), api_key, search_engine_id);
    }

    @Override
    public String toString() {
        return "GoogleEntity{" +
                "api_key='" + api_key + '\'' +
                ", search_engine_id='" + search_engine_id + '\'' +
                '}';
    }
}
