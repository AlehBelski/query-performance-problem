package com.technical.queryperformance.service;

import com.technical.queryperformance.configuration.CustomProperties;
import com.technical.queryperformance.exception.QueryExecutionException;
import com.technical.queryperformance.model.ExecutionResponseDTO;
import com.technical.queryperformance.model.QueryDTO;
import com.technical.queryperformance.service.measurement.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * The service with main logic of query performance.
 */
@Service
public class QueryService {

    private MeasurementService measurementService;
    private CustomProperties properties;
    private Map<String, JdbcTemplate> jdbcTemplates;
    private ExecutorService executorService;

    @Autowired
    public QueryService(MeasurementService measurementService, CustomProperties properties,
                        Map<String, JdbcTemplate> jdbcTemplates) {
        this.measurementService = measurementService;
        this.properties = properties;
        this.jdbcTemplates = jdbcTemplates;
        executorService = Executors.newFixedThreadPool(jdbcTemplates.size());
    }

    /**
     * Measure and prepare the response with the average execution time of the passed list of queries.
     *
     * @param queryList the list of queries to measure.
     * @return the list of average time per query.
     */
    public List<ExecutionResponseDTO> measureTheExecutionTime(List<QueryDTO> queryList) {
        List<ExecutionResponseDTO> responseDTOS = new ArrayList<>();

        List<Callable<ExecutionResponseDTO>> tasks = prepareListOfTasks(queryList);

        try {
            List<Future<ExecutionResponseDTO>> futures = executorService.invokeAll(tasks);
            for (Future<ExecutionResponseDTO> future : futures) {
                responseDTOS.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new QueryExecutionException(e);
        }

        return responseDTOS;
    }

    /**
     * Prepare the list of tasks that will be executed against the databases.
     *
     * @param queryList the passed queries from the request.
     * @return the list of {@link Callable} to execute.
     */
    private List<Callable<ExecutionResponseDTO>> prepareListOfTasks(List<QueryDTO> queryList) {
        List<Callable<ExecutionResponseDTO>> tasks = new ArrayList<>();
        int numberOfExecutions = properties.getExecutionNumber();

        jdbcTemplates.forEach((db, temp) -> {
            for (QueryDTO queryDTO : queryList) {
                String query = queryDTO.getQuery();
                tasks.add(() -> {
                    long averageTime = measurementService.measureAverageTime(query, temp, numberOfExecutions);
                    return new ExecutionResponseDTO(db, query, averageTime);
                });
            }
        });

        return tasks;
    }
}
