package com.aalzatea.todo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

@Configuration
public class JsonMapperConfiguration {

    @Value("${app.json-configuration.date-format}")
    private String dateFormat;

    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @Value("app.json-configuration.timezone")
    private String timeZone;

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();

        configureDateFormat(objectMapper);
        configureJSONMapperSerializationFeatures(objectMapper);
        configureJSONMapperDeserializationFeatures(objectMapper);

        return objectMapper;
    }

    private void configureDateFormat(ObjectMapper objectMapper) {
        var dateFormatValue = StringUtils.hasText(dateFormat) ?
                dateFormat :
                DATE_FORMAT_DEFAULT;

        var timeZoneValue = StringUtils.hasText(timeZone) ?
                TimeZone.getTimeZone(ZoneId.of(timeZone)) :
                TimeZone.getDefault();

        objectMapper.setDateFormat(new SimpleDateFormat(dateFormatValue));
        objectMapper.setTimeZone(timeZoneValue);
    }

    private void configureJSONMapperSerializationFeatures(ObjectMapper objectMapper) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.enable(
                SerializationFeature.INDENT_OUTPUT,
                SerializationFeature.WRITE_ENUMS_USING_TO_STRING
        );

        objectMapper.disable(
                SerializationFeature.FAIL_ON_EMPTY_BEANS
        );
    }

    private void configureJSONMapperDeserializationFeatures(ObjectMapper objectMapper) {
        objectMapper.enable(
                DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT,
                DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
                DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS,
                DeserializationFeature.READ_ENUMS_USING_TO_STRING
        );

        objectMapper.disable(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        );
    }
}
