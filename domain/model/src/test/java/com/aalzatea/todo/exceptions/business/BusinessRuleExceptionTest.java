package com.aalzatea.todo.exceptions.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BusinessRuleExceptionTest {

    @Test
    @DisplayName("Test building a business rule exception instance with message parameters")
    void testBusinessRuleExceptionBuildWithMsgParameters() {
        var exception = BusinessRuleException.Type.BUSINESS_RULE_EXCEPTION
                .buildWithMsgParameters("Person");
        var expectedMessage = "domain.msg.business_rule.general";

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals("Person", exception.getMessageParameters()[0]);
    }
}