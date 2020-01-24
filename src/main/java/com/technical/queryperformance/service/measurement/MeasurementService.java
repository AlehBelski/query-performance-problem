package com.technical.queryperformance.service.measurement;

import org.springframework.jdbc.core.JdbcTemplate;

public interface MeasurementService {
    long measureAverageTime(String query, JdbcTemplate template, int numberOfExecutions);
}
