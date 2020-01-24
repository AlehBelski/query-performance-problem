package com.technical.queryperformance.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties("list")
public class DatabasePropertiesList {
    private List<DatabaseProperties> dataBases = new ArrayList<>();

    public List<DatabaseProperties> getDataBases() {
        return dataBases;
    }

    public void setDataBases(List<DatabaseProperties> dataBases) {
        this.dataBases = dataBases;
    }
}
