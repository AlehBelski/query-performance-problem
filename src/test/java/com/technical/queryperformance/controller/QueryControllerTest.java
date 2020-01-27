package com.technical.queryperformance.controller;

import com.technical.queryperformance.exception.BlankQueryRequestException;
import com.technical.queryperformance.exception.InvalidQueryException;
import com.technical.queryperformance.model.ExecutionResponseDTO;
import com.technical.queryperformance.model.QueryDTO;
import com.technical.queryperformance.service.QueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QueryControllerTest {

    @Mock
    private QueryService queryService;

    @InjectMocks
    private QueryController queryController;

    @Test
    public void shouldThrowErrorWhenQueryListIsNull() {
        try {
            queryController.measureTheExecutionTime(null);
            fail("The exceptions should be thrown");
        } catch (RuntimeException ex) {
            assertEquals(BlankQueryRequestException.class, ex.getClass());
            assertNotNull(ex.getMessage());
            assertEquals("The passed body in the request should not be empty.", ex.getMessage());
        }
    }

    @Test
    public void shouldThrowErrorWhenAnyOfQueryIsNullOrEmpty() {
        try {
            queryController.measureTheExecutionTime(Collections.singletonList(new QueryDTO()));
            fail("The exceptions should be thrown");
        } catch (RuntimeException ex) {
            assertEquals(InvalidQueryException.class, ex.getClass());
            assertNotNull(ex.getMessage());
            assertEquals("Queries should not be empty.", ex.getMessage());
        }
    }

    @Test
    public void shouldSuccessfullyProcessTheRequest() {
        QueryDTO query = new QueryDTO();
        query.setQuery("SELECT * FROM test");

        List<ExecutionResponseDTO> exceptedResponse = new ArrayList<>();
        List<QueryDTO> request = Collections.singletonList(query);

        when(queryService.measureTheExecutionTime(request)).thenReturn(exceptedResponse);

        List<ExecutionResponseDTO> actualResponse = queryController.measureTheExecutionTime(request);

        assertEquals(exceptedResponse.size(), actualResponse.size());
        assertEquals(exceptedResponse, actualResponse);

        verify(queryService).measureTheExecutionTime(request);
        verifyNoMoreInteractions(queryService);

    }
}
