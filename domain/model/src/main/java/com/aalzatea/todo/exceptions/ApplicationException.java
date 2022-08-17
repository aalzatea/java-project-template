package com.aalzatea.todo.exceptions;

import com.aalzatea.todo.exceptions.messages.ErrorMessage;
import lombok.Getter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Getter
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6525893591208400418L;

    private final ErrorMessage errorMessage;

    protected ApplicationException(String errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorMessage = buildErrorMessage(errorCode);
    }

    private ErrorMessage buildErrorMessage(String errorCode) {
        var currentDate = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();

        return new ErrorMessage(errorCode, this.getMessage(), Date.from(currentDate));
    }

    protected static String formatMessage(String message, Object... messageArgs) {
        return Objects.nonNull(message) && Objects.nonNull(messageArgs) ?
                String.format(message, messageArgs) : message;
    }
}
