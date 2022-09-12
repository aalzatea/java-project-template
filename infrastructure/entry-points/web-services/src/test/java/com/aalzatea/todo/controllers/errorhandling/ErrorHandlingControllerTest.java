package com.aalzatea.todo.controllers.errorhandling;

import com.aalzatea.todo.exceptions.business.BusinessRuleException;
import com.aalzatea.todo.exceptions.business.DataNotFoundException;
import com.aalzatea.todo.exceptions.business.DataValidationException;
import com.aalzatea.todo.exceptions.external.ExternalServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Collections;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorHandlingControllerTest {

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private ErrorHandlingController underTest;

    @Test
    void testHandleDataValidationException() {
        var messages = Collections.singletonList("Test");
        var exception = DataValidationException.Type.DATA_VALIDATION_EXCEPTION.build(messages);
        var expectedMessage = "The below data are incorrect:\n" +
                "- Test";

        when(messageSource.getMessage(eq(exception.getMessage()),
                eq(exception.getMessageParameters()),
                eq(null),
                eq(Locale.ENGLISH))).thenReturn(expectedMessage);

        var errorMessage = underTest.handleDataValidationException(exception);

        assertNotNull(errorMessage);
        assertEquals("400", errorMessage.getCode());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());

        verify(messageSource).getMessage(eq(exception.getMessage()), eq(exception.getMessageParameters()), eq(null),
                eq(Locale.ENGLISH));
    }

    @Test
    void testHandleDataNotFoundException() {
        var exception = DataNotFoundException.Type.DATA_NOT_FOUND_EXCEPTION.build();
        var expectedMessage = "Data not found.";

        when(messageSource.getMessage(eq(exception.getMessage()),
                eq(exception.getMessageParameters()),
                eq(null),
                eq(Locale.ENGLISH))).thenReturn(expectedMessage);

        var errorMessage = underTest.handleDataNotFoundException(exception);

        assertNotNull(errorMessage);
        assertEquals("404", errorMessage.getCode());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());

        verify(messageSource).getMessage(eq(exception.getMessage()), eq(exception.getMessageParameters()), eq(null),
                eq(Locale.ENGLISH));
    }

    @Test
    void testHandleBusinessRuleException() {
        var exception = BusinessRuleException.Type.BUSINESS_RULE_EXCEPTION.buildWithMsgParameters("Test");
        var expectedMessage = "This is a business rule exception message: 'Test'";

        when(messageSource.getMessage(eq(exception.getMessage()),
                eq(exception.getMessageParameters()),
                eq(null),
                eq(Locale.ENGLISH))).thenReturn(expectedMessage);

        var errorMessage = underTest.handleBusinessRuleException(exception);

        assertNotNull(errorMessage);
        assertEquals("409", errorMessage.getCode());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());

        verify(messageSource).getMessage(eq(exception.getMessage()), eq(exception.getMessageParameters()), eq(null),
                eq(Locale.ENGLISH));
    }

    @Test
    void testHandleExternalServiceException() {
        var exception = ExternalServiceException.Type.EXTERNAL_SERVICE_EXCEPTION.build(new IllegalArgumentException());
        var expectedMessage = "The external service is unavailable at this moment. Please try again in a couple of minutes.";

        when(messageSource.getMessage(eq(exception.getMessage()),
                eq(exception.getMessageParameters()),
                eq(null),
                eq(Locale.ENGLISH))).thenReturn(expectedMessage);

        var errorMessage = underTest.handleExternalServiceException(exception);

        assertNotNull(errorMessage);
        assertEquals("503", errorMessage.getCode());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertNotNull(errorMessage.getErrorDate());

        verify(messageSource).getMessage(eq(exception.getMessage()), eq(exception.getMessageParameters()), eq(null),
                eq(Locale.ENGLISH));
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