package com.aalzatea.commons.beans.mapper.config;

import org.junit.jupiter.api.Test;
import org.modelmapper.spi.PropertyType;

import static org.junit.jupiter.api.Assertions.*;

class LombokBuilderNamingConventionTest {

    @Test
    void testApplies() {
        var nameConvention = LombokBuilderNamingConvention.INSTANCE;

        var result = nameConvention.applies("test", PropertyType.METHOD);

        assertTrue(result);
    }

    @Test
    void testAppliesWithFalseValue() {
        var nameConvention = LombokBuilderNamingConvention.INSTANCE;

        var result = nameConvention.applies("test", PropertyType.FIELD);

        assertFalse(result);
    }

    @Test
    void testToString() {
        var nameConvention = LombokBuilderNamingConvention.INSTANCE;

        var result = nameConvention.toString();

        assertNotNull(result);
        assertEquals("Lombok @Builder Naming Convention", result);
    }
}