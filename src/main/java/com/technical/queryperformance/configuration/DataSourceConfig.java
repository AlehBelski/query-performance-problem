package com.technical.queryperformance.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private static final int MAX_CONNECTIONS = 1;

    @Bean
    public Map<String, JdbcTemplate> jdbcTemplates(DatabasePropertiesList properties) {
        Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();

        List<DatabaseProperties> databaseProperties = properties.getDataBases();
        for (DatabaseProperties property : databaseProperties) {
            JdbcTemplate template = new JdbcTemplate(prepareDataSource(property));
            template.setIgnoreWarnings(false);

            jdbcTemplates.put(property.getDbName(), template);
        }

        return jdbcTemplates;
    }

    public DataSource prepareDataSource(DatabaseProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUserName());
        dataSource.setPassword(properties.getPassword());
        dataSource.setMaximumPoolSize(MAX_CONNECTIONS);

        return dataSource;
    }

}
