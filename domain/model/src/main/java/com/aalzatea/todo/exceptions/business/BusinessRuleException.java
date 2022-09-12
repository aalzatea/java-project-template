package com.aalzatea.todo.exceptions.business;

import com.aalzatea.todo.exceptions.ApplicationException;
import lombok.Getter;

import java.io.Serial;

@Getter
public class BusinessRuleException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = 4878508180181120205L;

    public enum Type {

        BUSINESS_RULE_EXCEPTION("409", "domain.msg.business_rule.general");

        private final String code;

        private final String message;

        Type(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public BusinessRuleException buildWithMsgParameters(Object... messageParameters) {
            return new BusinessRuleException(this, this.message, messageParameters);
        }
    }

    private final Type type;

    private BusinessRuleException(Type type, String message, Object... messageParameters) {
        super(type.code, message, null, messageParameters);
        this.type = type;
    }
}
