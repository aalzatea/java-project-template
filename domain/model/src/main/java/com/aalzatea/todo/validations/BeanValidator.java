package com.aalzatea.todo.validations;

import com.aalzatea.todo.exceptions.business.DataValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BeanValidator {

    public static final String ERROR_FIELD_MESSAGE_FORMAT = "Property '%s' with value '%s' is invalid for this reason: %s";

    public static void validateBean(Object object) {
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();

        var violations = validator.validate(object).stream()
                .map(BeanValidator::getErrorFieldFullMessage)
                .collect(Collectors.toList());

        if(violations.isEmpty()) {
            return;
        }

        throw DataValidationException.Type.DATA_VALIDATION_EXCEPTION.build(violations);
    }

    private static String getErrorFieldFullMessage(ConstraintViolation<?> constraintViolation) {
        var fieldName = getPropertyName(constraintViolation);

        return String.format(
                ERROR_FIELD_MESSAGE_FORMAT,
                fieldName,
                constraintViolation.getInvalidValue(),
                constraintViolation.getMessage()
        );
    }

    private static String getPropertyName(ConstraintViolation<?> constraintViolation) {
        return StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false)
                .map(Path.Node::getName)
                .reduce((first, second) -> second)
                .orElse(null);
    }
}