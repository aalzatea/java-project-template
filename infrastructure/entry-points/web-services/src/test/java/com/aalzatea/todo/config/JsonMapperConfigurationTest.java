package com.aalzatea.todo.config;

import com.aalzatea.todo.config.json.JsonMapperConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonMapperConfigurationTest {

    @Test
    @DisplayName("test get ObjectMapper Not Null")
    void testGetObjectMapper() {
        var objectMapper = new JsonMapperConfiguration().objectMapper();

        assertNotNull(objectMapper);
    }
}