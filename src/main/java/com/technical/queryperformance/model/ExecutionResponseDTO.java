package com.technical.queryperformance.model;

import java.util.Objects;

/**
 * The class providing information about the returned response.
 */
public class ExecutionResponseDTO {
    private String database;
    private String query;
    private long averageTime;

    public ExecutionResponseDTO() {
    }

    public ExecutionResponseDTO(String database, String query, long averageTime) {
        this.database = database;
        this.query = query;
        this.averageTime = averageTime;
    }

    public String getDatabase() {
        return database;
    }

    public String getQuery() {
        return query;
    }

    public long getAverageTime() {
        return averageTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutionResponseDTO that = (ExecutionResponseDTO) o;
        return averageTime == that.averageTime &&
                Objects.equals(database, that.database) &&
                Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database, query, averageTime);
    }
}
