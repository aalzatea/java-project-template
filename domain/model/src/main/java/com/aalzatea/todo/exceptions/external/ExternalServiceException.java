package com.aalzatea.todo.exceptions.external;

import com.aalzatea.todo.exceptions.ApplicationException;
import lombok.Getter;

import java.io.Serial;

@Getter
public class ExternalServiceException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = 4551676000325885846L;

    public enum Type {

        EXTERNAL_SERVICE_EXCEPTION("503", "The external service is unavailable at this moment. Please try again in a couple of minutes.");

        private final String code;

        private final String message;

        Type(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public ExternalServiceException build(Throwable throwable) {
            return new ExternalServiceException(this, throwable);
        }
    }

    private final Type type;

    private ExternalServiceException(Type type, Throwable throwable) {
        super(type.code, type.message, throwable);
        this.type = type;
    }
}
