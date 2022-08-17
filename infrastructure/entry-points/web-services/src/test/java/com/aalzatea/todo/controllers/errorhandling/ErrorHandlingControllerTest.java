package com.aalzatea.todo.controllers.errorhandling;

import com.aalzatea.todo.exceptions.business.BusinessRuleException;
import com.aalzatea.todo.exceptions.business.DataNotFoundException;
import com.aalzatea.todo.exceptions.business.DataValidationException;
import com.aalzatea.todo.exceptions.external.ExternalServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ErrorHandlingControllerTest {

    private ErrorHandlingController underTest;

    @BeforeEach
    public void init() {
        underTest = new ErrorHandlingController();
    }

    @AfterEach
    public void clean() {
        underTest = null;
    }

    @Test
    void testHandleDataValidationException() {
        var messages = Collections.singletonList("Test");
        var exception = DataValidationException.Type.DATA_VALIDATION_EXCEPTION.build(messages);
        var expectedMessage = "The below data are incorrect:\n" +
                "- Test";

        var errorMessage = underTest.handleDataValidationException(exception);

        assertNotNull(errorMessage);
        assertEquals("400", errorMessage.getCode());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());
    }

    @Test
    void testHandleDataNotFoundException() {
        var exception = DataNotFoundException.Type.DATA_NOT_FOUND_EXCEPTION.build();
        var expectedMessage = "Data not found.";

        var errorMessage = underTest.handleDataNotFoundException(exception);

        assertNotNull(errorMessage);
        assertEquals("404", errorMessage.getCode());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());
    }

    @Test
    void testHandleBusinessRuleException() {
        var exception = BusinessRuleException.Type.BUSINESS_RULE_EXCEPTION.buildWithMsgParameters("Test");
        var expectedMessage = "This is a business rule exception message: 'Test'";

        var errorMessage = underTest.handleBusinessRuleException(exception);

        assertNotNull(errorMessage);
        assertEquals("409", errorMessage.getCode());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());
    }

    @Test
    void testHandleExternalServiceException() {
        var exception = ExternalServiceException.Type.EXTERNAL_SERVICE_EXCEPTION.build(new IllegalArgumentException());
        var expectedMessage = "The external service is unavailable at this moment. Please try again in a couple of minutes.";

        var errorMessage = underTest.handleExternalServiceException(exception);

        assertNotNull(errorMessage);
        assertEquals("503", errorMessage.getCode());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());
    }

    @Test
    void testHandleException() {
        var exception = new IllegalArgumentException();

        var errorMessage = underTest.handleException(exception);

        assertNotNull(errorMessage);
        assertEquals("500", errorMessage.getCode());
        assertEquals(exception.getMessage(), errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());
    }
}