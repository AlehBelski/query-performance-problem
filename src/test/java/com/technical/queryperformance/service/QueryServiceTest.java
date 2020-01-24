package com.technical.queryperformance.service;

import com.technical.queryperformance.configuration.CustomProperties;
import com.technical.queryperformance.model.ExecutionResponseDTO;
import com.technical.queryperformance.model.QueryDTO;
import com.technical.queryperformance.service.measurement.MeasurementService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QueryServiceTest {

    @Mock
    private MeasurementService measurementService;

    @Mock
    private CustomProperties properties;

    @Mock
    private JdbcTemplate template;

    private Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();

    private QueryService queryService;

    @BeforeEach
    public void init() {
        jdbcTemplates.put("testDB", template);

        queryService = new QueryService(measurementService, properties, jdbcTemplates);
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplates.clear();
    }

    @Test
    public void shouldSuccessfullyReturnAResponse() {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setQuery("SELECT * FROM test");
        ExecutionResponseDTO expectedResponse = new ExecutionResponseDTO("testDB", queryDTO.getQuery(), 1L);

        when(properties.getExecutionNumber()).thenReturn(10);
        when(measurementService.measureAverageTime(queryDTO.getQuery(), template, 10)).thenReturn(1L);

        List<ExecutionResponseDTO> actualResponses = queryService.measureTheExecutionTime(Collections.singletonList(queryDTO));

        assertEquals(1, actualResponses.size());

        ExecutionResponseDTO actualResponse = actualResponses.get(0);
        assertEquals(expectedResponse, actualResponse);

        verify(properties).getExecutionNumber();
        verify(measurementService).measureAverageTime(queryDTO.getQuery(), template, 10);
        verifyNoMoreInteractions(properties, measurementService);
    }
}
