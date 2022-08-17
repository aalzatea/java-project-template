package com.aalzatea.todo.exceptions.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataNotFoundExceptionTest {

    @Test
    @DisplayName("Test building a data not found exception instance")
    void testDataNotFoundExceptionBuild() {
        var exception = DataNotFoundException.Type.DATA_NOT_FOUND_EXCEPTION.build();
        var expectedMessage = "Data not found.";

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(expectedMessage, exception.getErrorMessage().getMessage());
    }

    @Test
    @DisplayName("Test building a data not found exception instance with message parameters")
    void testDataNotFoundExceptionBuildWithMsgParameters() {
        var exception = DataNotFoundException.Type.DATA_WITH_ID_NOT_FOUND_EXCEPTION
                .buildWithMsgParameters("Person", 1);
        var expectedMessage = "The data for 'Person' with id '1' has not been found.";

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(expectedMessage, exception.getErrorMessage().getMessage());
    }
}