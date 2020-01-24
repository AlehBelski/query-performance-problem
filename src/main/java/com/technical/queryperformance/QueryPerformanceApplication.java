package com.technical.queryperformance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//TODO add tests,readme and javadoc
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class QueryPerformanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryPerformanceApplication.class, args);
    }

}
