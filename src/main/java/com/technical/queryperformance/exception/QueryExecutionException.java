package com.technical.queryperformance.exception;

/**
 * The exception that will be thrown in the case when something when went wrong while the query execution.
 */
public class QueryExecutionException extends RuntimeException {
    public QueryExecutionException() {
        super();
    }

    public QueryExecutionException(String message) {
        super(message);
    }

    public QueryExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryExecutionException(Throwable cause) {
        super(cause);
    }

    protected QueryExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
