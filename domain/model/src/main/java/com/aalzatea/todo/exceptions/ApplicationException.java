package com.aalzatea.todo.exceptions;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6525893591208400418L;

    private final String errorCode;

    private Object[] messageParameters;

    protected ApplicationException(String errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    protected ApplicationException(String errorCode, String message, Throwable throwable, Object... messageParameters) {
        super(message, throwable);
        this.errorCode = errorCode;
        this.messageParameters = messageParameters;
    }
}
