package com.aalzatea.todo.exceptions.business;

import com.aalzatea.todo.exceptions.ApplicationException;
import lombok.Getter;

import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class DataValidationException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = 5387753438905538422L;

    public enum Type {

        DATA_VALIDATION_EXCEPTION("400", "domain.msg.data_validation.general");

        private final String code;

        private final String message;

        Type(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public DataValidationException build(List<String> messages) {
            var messageParameter = buildExceptionMessage(messages);
            return new DataValidationException(this, messageParameter);
        }

        private String buildExceptionMessage(List<String> messages) {
            return messages.stream()
                    .map(m -> "- " + m)
                    .collect(Collectors.joining("\n"));
        }
    }

    private final Type type;

    private DataValidationException(Type type, Object... messageParameters) {
        super(type.code, type.message, null, messageParameters);
        this.type = type;
    }
}
