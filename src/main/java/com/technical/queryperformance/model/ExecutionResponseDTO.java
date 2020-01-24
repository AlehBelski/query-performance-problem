package com.technical.queryperformance.model;

public class ExecutionResponseDTO {
    private String database;
    private String query;
    private long averageTime;

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
}
