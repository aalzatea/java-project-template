package com.aalzatea.commons.beans.mapper.config;

import org.junit.jupiter.api.Test;
import org.modelmapper.spi.NameableType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LombokBuilderNameTransformerTest {

    @Test
    void testTransform() {
        var nameTransformer = LombokBuilderNameTransformer.INSTANCE;

        var result = nameTransformer.transform("TestMethod", NameableType.FIELD);

        assertNotNull(result);
        assertEquals("testMethod", result);
    }

    @Test
    void testToString() {
        var nameTransformer = LombokBuilderNameTransformer.INSTANCE;

        var result = nameTransformer.toString();

        assertNotNull(result);
        assertEquals("Lombok @Builder Mutator", result);
    }
}