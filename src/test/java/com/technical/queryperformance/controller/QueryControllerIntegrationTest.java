package com.technical.queryperformance.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.queryperformance.QueryPerformanceApplication;
import com.technical.queryperformance.model.ExecutionResponseDTO;
import com.technical.queryperformance.model.QueryDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = QueryPerformanceApplication.class
)
@AutoConfigureMockMvc
public class QueryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Map<String, JdbcTemplate> jdbcTemplates;

    @BeforeEach
    public void init() {
        JdbcTemplate template = jdbcTemplates.get("derby");

        template.execute("CREATE TABLE entity (id int, name varchar(255))");
    }

    @AfterEach
    public void cleanup() {
        JdbcTemplate template = jdbcTemplates.get("derby");

        template.execute("DROP TABLE entity");
    }

    @Test
    public void shouldProcessWithoutError() throws Exception {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setQuery("SELECT * FROM entity");

        List<QueryDTO> queries = Collections.singletonList(queryDTO);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(queries);

        MvcResult results = mockMvc.perform(post("/performance/measure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        List<ExecutionResponseDTO> response = mapper.readValue(results.getResponse().getContentAsString(),
                new TypeReference<List<ExecutionResponseDTO>>() {
                });

        assertEquals(response.size(), 1);

        for (ExecutionResponseDTO responseDTO : response) {
            assertNotNull(responseDTO.getQuery());
            assertNotNull(responseDTO.getDatabase());
            assertTrue(responseDTO.getAverageTime() >= 0L);
        }

    }
}
