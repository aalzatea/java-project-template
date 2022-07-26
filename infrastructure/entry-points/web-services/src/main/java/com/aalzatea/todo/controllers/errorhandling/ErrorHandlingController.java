package com.aalzatea.todo.controllers.errorhandling;

import com.aalzatea.todo.exceptions.ApplicationException;
import com.aalzatea.todo.exceptions.business.BusinessRuleException;
import com.aalzatea.todo.exceptions.business.DataNotFoundException;
import com.aalzatea.todo.exceptions.business.DataValidationException;
import com.aalzatea.todo.exceptions.external.ExternalServiceException;
import com.aalzatea.todo.exceptions.messages.ErrorMessage;
import com.aalzatea.todo.validations.BeanValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandlingController {

    private final MessageSource messageSource;

    @ExceptionHandler(DataValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDataValidationException(DataValidationException dataValidationException) {
        return getErrorMessage(dataValidationException);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleDataValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        var messages = methodArgumentNotValidException.getBindingResult().getAllErrors()
                .stream()
                .map(error ->
                    String.format(
                            BeanValidator.ERROR_FIELD_MESSAGE_FORMAT,
                            ((FieldError) error).getField(),
                            ((FieldError) error).getRejectedValue(),
                            error.getDefaultMessage()
                    )
                ).collect(Collectors.toUnmodifiableList());

        var exception = DataValidationException.Type.DATA_VALIDATION_EXCEPTION
                .build(messages);

        return getErrorMessage(exception);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleDataNotFoundException(DataNotFoundException dataNotFoundException) {
        return getErrorMessage(dataNotFoundException);
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleBusinessRuleException(BusinessRuleException businessRuleException) {
        return getErrorMessage(businessRuleException);
    }

    @ExceptionHandler(ExternalServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorMessage handleExternalServiceException(ExternalServiceException externalServiceException) {
        return getErrorMessage(externalServiceException);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception exception) {
        return getErrorMessage(exception);
    }

    private ErrorMessage getErrorMessage(ApplicationException applicationException) {
        var message = messageSource.getMessage(applicationException.getMessage(),
                applicationException.getMessageParameters(),
                null,
                Locale.ENGLISH);
        var currentDate = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();

        logger.error(message, applicationException);

        return new ErrorMessage(applicationException.getErrorCode(), message, Date.from(currentDate));
    }

    private ErrorMessage getErrorMessage(Exception exception) {
        logger.error(exception.getMessage(), exception);

        var code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ErrorMessage(code, exception.getMessage(), new Date());
    }

    private String formatMessage(String message, Object... messageArgs) {
        return Objects.nonNull(message) && Objects.nonNull(messageArgs) ?
                String.format(message, messageArgs) : message;
    }
}
