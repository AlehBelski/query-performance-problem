package com.technical.queryperformance.util;

import com.technical.queryperformance.model.QueryDTO;

import java.security.InvalidParameterException;
import java.util.List;

//todo add javadoc
public class QueryValidationUtil {

    private static final String INVALID_PARAM_MESSAGE = "Query should not be empty"; //fixme add real message

    public static void validateElementsNotNullOrEmpty(List<QueryDTO> queryList) {
        for (QueryDTO queryDTO : queryList) {
            String query = queryDTO.getQuery();
            if (query == null || query.isEmpty()) {
                throw new InvalidParameterException(INVALID_PARAM_MESSAGE); //todo add custom exception
            }
        }
    }
}
