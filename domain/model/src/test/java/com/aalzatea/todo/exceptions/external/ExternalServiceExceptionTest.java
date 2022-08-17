package com.aalzatea.todo.exceptions.external;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExternalServiceExceptionTest {

    @Test
    @DisplayName("Test building a business rule exception instance with message parameters")
    void testDataNotFoundExceptionBuildWithMsgParameters() {
        var exception = ExternalServiceException.Type.EXTERNAL_SERVICE_EXCEPTION
                .build(new IllegalArgumentException());
        var expectedMessage = "The external service is unavailable at this moment. Please try again in a couple of minutes.";

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(expectedMessage, exception.getErrorMessage().getMessage());
    }
}