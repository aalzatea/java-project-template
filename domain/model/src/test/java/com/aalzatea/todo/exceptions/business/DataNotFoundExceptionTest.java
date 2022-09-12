package com.aalzatea.todo.exceptions.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DataNotFoundExceptionTest {

    @Test
    @DisplayName("Test building a data not found exception instance")
    void testDataNotFoundExceptionBuild() {
        var exception = DataNotFoundException.Type.DATA_NOT_FOUND_EXCEPTION.build();
        var expectedMessage = "domain.msg.data_not_found.general";

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertNull(exception.getMessageParameters());
    }

    @Test
    @DisplayName("Test building a data not found exception instance with message parameters")
    void testDataNotFoundExceptionBuildWithMsgParameters() {
        var exception = DataNotFoundException.Type.DATA_WITH_ID_NOT_FOUND_EXCEPTION
                .buildWithMsgParameters("Person", 1);
        var expectedMessage = "domain.msg.data_not_found.id_not_found";

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(2, exception.getMessageParameters().length);
    }
}