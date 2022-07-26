package com.aalzatea.todo.exceptions.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataValidationExceptionTest {

    @Test
    @DisplayName("Test building a data validation exception instance with many error messages")
    void testDataValidationExceptionBuildWithMsgParameters() {
        var messages = Arrays.asList("Violation data message 1", "Violation data message 2", "Violation data message 3");
        var exception = DataValidationException.Type.DATA_VALIDATION_EXCEPTION
                .build(messages);
        var expectedMessage = "domain.msg.data_validation.general";
        var expectedMessageParameters = """
                - Violation data message 1
                - Violation data message 2
                - Violation data message 3""";

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(expectedMessageParameters, exception.getMessageParameters()[0]);
    }
}