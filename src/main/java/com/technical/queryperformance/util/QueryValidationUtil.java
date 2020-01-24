package com.technical.queryperformance.util;

import com.technical.queryperformance.exception.BlankQueryRequestException;
import com.technical.queryperformance.exception.InvalidQueryException;
import com.technical.queryperformance.model.QueryDTO;

import java.util.List;

//todo add javadoc
public class QueryValidationUtil {

    public static void validateQueriesArePresent(List<QueryDTO> queryList) {
        if (queryList == null || queryList.isEmpty()) {
            throw new BlankQueryRequestException("The passed body in the request should not be empty.");
        }
    }

    public static void validateElementsNotNullOrEmpty(List<QueryDTO> queryList) {
        for (QueryDTO queryDTO : queryList) {
            String query = queryDTO.getQuery();
            if (query == null || query.isEmpty()) {
                throw new InvalidQueryException("Queries should not be empty.");
            }
        }
    }
}
