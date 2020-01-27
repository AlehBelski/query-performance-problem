package com.technical.queryperformance.service.measurement;

import com.technical.queryperformance.service.measurement.MeasurementService;
import com.technical.queryperformance.service.measurement.StopWatchMeasurementService;
import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StopWatchMeasurementServiceTest {

    private MeasurementService measurementService = new StopWatchMeasurementService();

    @Test
    public void test() {
        String query = "SELECT * FROM test";
        int numberOfExecutions = 5;

        JdbcTemplate template = mock(JdbcTemplate.class);

        doNothing().when(template).execute(query);

        long actualResult = measurementService.measureAverageTime(query, template, numberOfExecutions);

        assertTrue(actualResult >= 0);

        verify(template, times(numberOfExecutions + 1)).execute(query);
        verifyNoMoreInteractions(template);
    }

}
