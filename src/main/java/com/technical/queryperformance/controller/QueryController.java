package com.technical.queryperformance.controller;

import com.technical.queryperformance.model.QueryDTO;
import com.technical.queryperformance.model.ExecutionResponseDTO;
import com.technical.queryperformance.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.technical.queryperformance.util.QueryValidationUtil.validateElementsNotNullOrEmpty;

//fixme add javadoc
@RestController
@RequestMapping("/performance")
public class QueryController {

    @Autowired
    private QueryService service;

    @PostMapping(consumes = "application/json", produces = "application/json",
            path = "/measure")
    public List<ExecutionResponseDTO> measureTheExecutionTime(@RequestBody List<QueryDTO> queryList) { //fixme add custom error when list is null
        validateElementsNotNullOrEmpty(queryList);
        return service.measureTheExecutionTime(queryList);
    }
}
