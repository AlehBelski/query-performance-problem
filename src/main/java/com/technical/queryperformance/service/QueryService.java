package com.technical.queryperformance.service;

import com.technical.queryperformance.configuration.CustomProperties;
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

    public List<ExecutionResponseDTO> measureTheExecutionTime(List<QueryDTO> queryList) { //fixme add exception?
        List<ExecutionResponseDTO> responseDTOS = new ArrayList<>();

        List<Callable<ExecutionResponseDTO>> tasks = prepareListOfTasks(queryList);

        try {
            List<Future<ExecutionResponseDTO>> futures = executorService.invokeAll(tasks);
            for (Future<ExecutionResponseDTO> future : futures) {
                responseDTOS.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            //fixme add handler
        }

        return responseDTOS;
    }

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
