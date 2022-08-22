package com.aalzatea.commons.test.beans.generators;

import com.aalzatea.commons.test.beans.pojos.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class BeanGeneratorTest {

    @Test
    @DisplayName("Generate a string value using the bean generator")
    void testGenerateBeanUsingString() {
        var strRandomValue = BeanGenerator.generateBean(String.class);

        logger.info("testGenerateBeanUsingString -> This is the generated value: {}", strRandomValue);

        assertNotNull(strRandomValue);
    }

    @ParameterizedTest(name = "{index} The value for \"{0}\" was generated using the bean generator")
    @ValueSource(classes = { Double.class, Float.class, Long.class, Integer.class, Short.class, Byte.class })
    void testGenerateBeanUsingNumber(Class<Number> numberClass) {
        var numberRandomValue = BeanGenerator.generateBean(numberClass);

        logger.info("testGenerateBeanUsingNumber -> This is the generated value: {}", numberRandomValue);

        assertNotNull(numberRandomValue);
    }

    @Test
    @DisplayName("Generate a date value using the bean generator")
    void testGenerateBeanUsingDate() {
        var dateRandomValue = BeanGenerator.generateBean(Date.class);

        logger.info("testGenerateBeanUsingDate -> This is the generated value: {}", dateRandomValue);

        assertNotNull(dateRandomValue);
    }

    @Test
    @DisplayName("Generate an object and fill its fields using the bean generator")
    void testGenerateBeanUsingObject() {
        var person = BeanGenerator.generateBean(Person.class);

        logger.info("testGenerateBeanUsingObject -> This is the generated object: {}", person);

        assertNotNull(person);
    }

    @Test
    @DisplayName("Generate a list and fill it with objects using the bean generator")
    void testGenerateBeanWithGenericTypesUsingList() {
        var personList = BeanGenerator.generateBeanWithGenericTypes(List.class, Person.class);

        logger.info("testGenerateBeanWithGenericTypesUsingList -> This is the generated list: {}", personList);

        assertNotNull(personList);
        assertNotEquals(0, personList.size());
    }

    @Test
    @DisplayName("Generate a map and fill it with objects using the bean generator")
    void testGenerateBeanWithGenericTypesUsingMap() {
        var personMap = BeanGenerator.generateBeanWithGenericTypes(Map.class, Integer.class, Person.class);

        logger.info("testGenerateBeanWithGenericTypesUsingMap -> This is the generated Map: {}", personMap);

        assertNotNull(personMap);
        assertNotEquals(0, personMap.size());
    }
}