package com.technical.queryperformance.util;

import com.technical.queryperformance.exception.BlankQueryRequestException;
import com.technical.queryperformance.exception.InvalidQueryException;
import com.technical.queryperformance.model.QueryDTO;

import java.util.List;

/**
 * The util class allows to validate the passed list of {@link QueryDTO} items.
 */
public class QueryValidationUtil {

    /**
     * Validates the list of {@link QueryDTO} items to null of empty.
     *
     * @param queryList to validate.
     * @throws BlankQueryRequestException if the passed list is null or empty.
     */
    public static void validateQueriesArePresent(List<QueryDTO> queryList) {
        if (queryList == null || queryList.isEmpty()) {
            throw new BlankQueryRequestException("The passed body in the request should not be empty.");
        }
    }

    /**
     * Validates the items of List {@link QueryDTO} to null of empty.
     *
     * @param queryList to validate.
     * @throws InvalidQueryException if any of items is null of empty.
     */
    public static void validateElementsNotNullOrEmpty(List<QueryDTO> queryList) {
        for (QueryDTO queryDTO : queryList) {
            String query = queryDTO.getQuery();
            if (query == null || query.isEmpty()) {
                throw new InvalidQueryException("Queries should not be empty.");
            }
        }
    }
}
