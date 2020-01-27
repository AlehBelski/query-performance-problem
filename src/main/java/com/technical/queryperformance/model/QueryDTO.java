package com.technical.queryperformance.model;

import java.util.Objects;

/**
 * The class containing the passed query.
 */
public class QueryDTO {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryDTO queryDTO = (QueryDTO) o;
        return Objects.equals(query, queryDTO.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query);
    }
}
