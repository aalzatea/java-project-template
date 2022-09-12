package com.aalzatea.todo.exceptions;

import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationExceptionTest {

    @Test
    @DisplayName("Test creating an application exception instance")
    void testApplicationException() {
        var code = BeanGenerator.generateBean(String.class);
        var message = BeanGenerator.generateBean(String.class);
        var exception = new IllegalArgumentException();

        var applicationException = new ApplicationException(code, message, exception);

        assertNotNull(applicationException);
        assertEquals(code, applicationException.getErrorCode());
        assertEquals(message, applicationException.getMessage());
        assertEquals(exception, applicationException.getCause());
    }

    @Test
    @DisplayName("Test creating an application exception instance with message parameters")
    void testApplicationExceptionWithMessageParameters() {
        var code = BeanGenerator.generateBean(String.class);
        var message = BeanGenerator.generateBean(String.class);
        var messageParameters = BeanGenerator.generateBean(Object[].class);
        var exception = new IllegalArgumentException();

        var applicationException = new ApplicationException(code, message, exception, messageParameters);

        assertNotNull(applicationException);
        assertEquals(code, applicationException.getErrorCode());
        assertEquals(message, applicationException.getMessage());
        assertEquals(messageParameters, applicationException.getMessageParameters());
        assertEquals(exception, applicationException.getCause());
    }
}