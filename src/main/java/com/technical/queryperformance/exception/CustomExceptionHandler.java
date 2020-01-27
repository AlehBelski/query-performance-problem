package com.technical.queryperformance.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The custom exception handler which allows to handle the exceptions thrown by the application
 * and modify {@link HttpStatus} codes and body responses.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidQueryException.class)
    protected ResponseEntity<Object> handleInvalidQueryException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = BlankQueryRequestException.class)
    protected ResponseEntity<Object> handleBlankQueryRequestException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = QueryExecutionException.class)
    protected ResponseEntity<Object> handleBlankQueryExecutionException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, "An error has occurred while the processing of the queries.",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
