package com.aalzatea.todo.exceptions.business;

import com.aalzatea.todo.exceptions.ApplicationException;
import lombok.Getter;

import java.io.Serial;

@Getter
public final class DataNotFoundException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = -5944305367401264464L;

    public enum Type {
        DATA_NOT_FOUND_EXCEPTION("404", "Data not found."),
        DATA_FOR_SOMETHING_NOT_FOUND_EXCEPTION("404", "The data for '%s' has not been found."),
        DATA_FOR_COLLECTION_NOT_FOUND_EXCEPTION("404", "'%s' don't exist with the provided parameters."),
        DATA_WITH_ID_NOT_FOUND_EXCEPTION("404", "The data for '%s' with id '%d' has not been found.");

        private final String code;

        private final String message;

        Type(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public DataNotFoundException build() {
            return new DataNotFoundException(this);
        }

        public DataNotFoundException buildWithMsgParameters(Object... messageParameters) {
            var msg = ApplicationException.formatMessage(this.message, messageParameters);
            return new DataNotFoundException(this, msg);
        }
    }

    private final Type type;

    private DataNotFoundException(Type type, String message) {
        super(type.code, message, null);
        this.type = type;
    }

    private DataNotFoundException(Type type) {
        super(type.code, type.message, null);
        this.type = type;
    }
}
