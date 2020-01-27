package com.technical.queryperformance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The main class which allow to run the application.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class QueryPerformanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryPerformanceApplication.class, args);
    }

}
