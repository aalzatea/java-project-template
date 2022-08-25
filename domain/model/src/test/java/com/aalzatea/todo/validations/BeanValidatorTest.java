package com.aalzatea.todo.validations;

import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import com.aalzatea.todo.exceptions.business.DataValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeanValidatorTest {

    @Test
    @DisplayName("Object is ok applying validate bean method")
    void testValidateBean() {
        var birthDate = Date.from(LocalDateTime.now().minus(10, ChronoUnit.YEARS).atZone(ZoneId.systemDefault()).toInstant());
        var person = BeanGenerator.generateBean(Person.class);
        person.setBirthDate(birthDate);
        person.setAge(person.getAge() + 12);

        assertDoesNotThrow(() -> BeanValidator.validateBean(person));
    }

    @Test
    @DisplayName("Object is not ok when all fields are null after applying validate bean method")
    void testValidateBeanWithAllFieldsNull() {
        var person = new Person(null, null, null, null);

        assertThrows(DataValidationException.class, () -> BeanValidator.validateBean(person));
    }

    @Test
    @DisplayName("Object is not ok when some values are not compliant rules after applying validate bean method")
    void testValidateBeanWithSomeValueAreNotCompliantRules() {
        var faker = Faker.instance();
        var birthDate = Date.from(
                LocalDate.now().plusYears(1)
                        .atStartOfDay().atZone(ZoneId.systemDefault())
                        .toInstant()
        );

        var person = new Person(
                faker.name().firstName(),
                faker.name().lastName(),
                10,
                birthDate
        );

        assertThrows(DataValidationException.class, () -> BeanValidator.validateBean(person));
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private static class Person {

        @NotBlank
        private String name;

        @NotBlank
        private String lastName;

        @NotNull
        @Min(12)
        private Integer age;

        @NotNull
        @Past
        private Date birthDate;
    }
}