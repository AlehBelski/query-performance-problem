package com.technical.queryperformance.service.measurement;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The service to measure the execution time.
 */
public interface MeasurementService {
    /**
     * This method allows to measure the average execution time of passed query against a database.
     *
     * @param query the query which will be executed and measured.
     * @param template the {@link JdbcTemplate} which allows to run the query against the database.
     * @param numberOfExecutions the number of executions the query which used in counting the average time.
     * @return the execution average time.
     */
    long measureAverageTime(String query, JdbcTemplate template, int numberOfExecutions);
}
