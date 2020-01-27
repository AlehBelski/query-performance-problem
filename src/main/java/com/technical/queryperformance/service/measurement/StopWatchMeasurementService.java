package com.technical.queryperformance.service.measurement;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * The service the measure the average time based on {@link StopWatch} library.
 */
@Service
public class StopWatchMeasurementService implements MeasurementService {

    @Override
    public long measureAverageTime(String query, JdbcTemplate template, int numberOfExecutions) {
        template.execute(query); //warm up

        long averageTime = 0L;
        StopWatch stopWatch = new StopWatch();

        for (int i = 0; i < numberOfExecutions; i++) {
            stopWatch.reset();
            stopWatch.start();
            template.execute(query);
            averageTime += stopWatch.getTime();
        }

        return averageTime / numberOfExecutions;
    }
}
