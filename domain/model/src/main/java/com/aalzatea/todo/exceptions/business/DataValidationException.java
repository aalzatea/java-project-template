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

        DATA_VALIDATION_EXCEPTION("400", "The below data are incorrect:\n%s");

        private final String code;

        private final String message;

        Type(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public DataValidationException build(List<String> messages) {
            var msg = buildExceptionMessage(messages);
            return new DataValidationException(this, msg);
        }

        private String buildExceptionMessage(List<String> messages) {
            var msg = messages.stream()
                    .map(m -> "- " + m)
                    .collect(Collectors.joining("\n"));

            return ApplicationException.formatMessage(this.message, msg);
        }
    }

    private final Type type;

    private DataValidationException(Type type, String message) {
        super(type.code, message, null);
        this.type = type;
    }
}
