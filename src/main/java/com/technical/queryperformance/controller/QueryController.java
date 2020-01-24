package com.technical.queryperformance.controller;

import com.technical.queryperformance.model.ExecutionResponseDTO;
import com.technical.queryperformance.model.QueryDTO;
import com.technical.queryperformance.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.technical.queryperformance.util.QueryValidationUtil.validateElementsNotNullOrEmpty;
import static com.technical.queryperformance.util.QueryValidationUtil.validateQueriesArePresent;

//fixme add javadoc
@RestController
@RequestMapping("/performance")
public class QueryController {

    @Autowired
    private QueryService service;

    @PostMapping(path = "/measure",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExecutionResponseDTO> measureTheExecutionTime(@RequestBody List<QueryDTO> queryList) {
        validateQueriesArePresent(queryList);
        validateElementsNotNullOrEmpty(queryList);

        return service.measureTheExecutionTime(queryList);
    }
}
