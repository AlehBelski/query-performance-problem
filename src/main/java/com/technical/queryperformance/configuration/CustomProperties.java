package com.technical.queryperformance.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("custom")
public class CustomProperties {
    private int executionNumber;

    public int getExecutionNumber() {
        return executionNumber;
    }

    public void setExecutionNumber(int executionNumber) {
        this.executionNumber = executionNumber;
    }
}
