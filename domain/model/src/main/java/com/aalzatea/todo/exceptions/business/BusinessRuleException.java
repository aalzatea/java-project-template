package com.aalzatea.todo.exceptions.business;

import com.aalzatea.todo.exceptions.ApplicationException;
import lombok.Getter;

import java.io.Serial;

@Getter
public class BusinessRuleException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = 4878508180181120205L;

    public enum Type {

        BUSINESS_RULE_EXCEPTION("409", "This is a business rule exception message: '%s'");

        private final String code;

        private final String message;

        Type(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public BusinessRuleException buildWithMsgParameters(Object... messageParameters) {
            var msg = ApplicationException.formatMessage(this.message, messageParameters);
            return new BusinessRuleException(this, msg);
        }
    }

    private final Type type;

    private BusinessRuleException(Type type, String message) {
        super(type.code, message, null);
        this.type = type;
    }
}
