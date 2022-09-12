package com.aalzatea.todo.exceptions.external;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ExternalServiceExceptionTest {

    @Test
    @DisplayName("Test building a external service exception instance")
    void testExternalServiceExceptionBuildWithMsgParameters() {
        var exception = ExternalServiceException.Type.EXTERNAL_SERVICE_EXCEPTION
                .build(new IllegalArgumentException());
        var expectedMessage = "domain.msg.external_service.general";

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertInstanceOf(IllegalArgumentException.class, exception.getCause());
        assertNull(exception.getMessageParameters());
    }
}