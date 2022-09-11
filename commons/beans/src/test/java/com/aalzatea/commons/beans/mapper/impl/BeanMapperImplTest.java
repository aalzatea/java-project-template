package com.aalzatea.commons.beans.mapper.impl;

import com.aalzatea.commons.beans.mapper.BeanMapper;
import com.aalzatea.commons.beans.mapper.pojos.Address;
import com.aalzatea.commons.beans.mapper.pojos.Person;
import com.aalzatea.commons.beans.mapper.pojos.PersonData;
import com.aalzatea.commons.beans.mapper.pojos.PersonDto;
import com.aalzatea.commons.test.beans.generators.BeanGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.MappingException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeanMapperImplTest {

    private BeanMapper beanMapper;

    @BeforeEach
    public void init() {
        beanMapper = new BeanMapperImpl();
    }

    @AfterEach
    public void clean() {
        beanMapper = null;
    }

    @Test
    void testMapDtoToEntityData() {
        var personDto = BeanGenerator.generateBean(PersonDto.class);

        var result = beanMapper.map(personDto, PersonData.class);

        assertNotNull(result);
        assertEquals(personDto.getName(), result.getName());
        assertEquals(personDto.getAge(), result.getAge());
        assertEquals(personDto.getBirthDate(), result.getBirthDate());
        assertEquals(personDto.getSingle(), result.getSingle());
        assertEquals(personDto.getGender().name(), result.getGender().name());
        assertEquals(personDto.getAddress().getCity(), result.getAddress().getCity());
    }

    @Test
    void testMapEntityDataToDto() {
        var personData = BeanGenerator.generateBean(PersonData.class);

        var result = beanMapper.map(personData, PersonDto.class);

        assertNotNull(result);
        assertEquals(personData.getName(), result.getName());
        assertEquals(personData.getAge(), result.getAge());
        assertEquals(personData.getBirthDate(), result.getBirthDate());
        assertEquals(personData.getSingle(), result.getSingle());
        assertEquals(personData.getGender().name(), result.getGender().name());
        assertEquals(personData.getAddress().getCity(), result.getAddress().getCity());
    }

    @Test
    void testMapDtoToEntity() {
        var personDto = BeanGenerator.generateBean(PersonDto.class);

        assertThrows(MappingException.class, () -> beanMapper.map(personDto, Person.class));
    }

    @Test
    void testMapEntityToDto() {
        var person = BeanGenerator.generateBean(Person.class);

        var result = beanMapper.map(person, PersonDto.class);

        assertNotNull(result);
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getAge(), result.getAge());
        assertEquals(person.getBirthDate(), result.getBirthDate());
        assertEquals(person.getSingle(), result.getSingle());
        assertEquals(person.getGender().name(), result.getGender().name());
        assertEquals(person.getAddress().getCity(), result.getAddress().getCity());
    }

    @Test
    @SuppressWarnings("unchecked")
    void mapCollection() {
        var personDtos = (List<PersonDto>) BeanGenerator.generateBeanWithGenericTypes(List.class, PersonDto.class);

        var result = beanMapper.mapCollection(personDtos, PersonData.class);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(personDtos.size(), result.size());
        assertInstanceOf(PersonData.class, result.get(0));
    }

    @Test
    void mapBuilder() {
        var personDto = BeanGenerator.generateBean(PersonDto.class);

        var addressResult = beanMapper.mapBuilder(personDto.getAddress(), Address.AddressBuilder.class)
                .build();
        var personResult = beanMapper.mapBuilder(personDto, Person.PersonBuilder.class)
                .address(addressResult)
                .build();

        assertNotNull(personResult);
        assertEquals(personDto.getName(), personResult.getName());
        assertEquals(personDto.getAge(), personResult.getAge());
        assertEquals(personDto.getBirthDate(), personResult.getBirthDate());
        assertEquals(personDto.getSingle(), personResult.getSingle());
        assertEquals(personDto.getGender().name(), personResult.getGender().name());
        assertEquals(personDto.getAddress().getCity(), personResult.getAddress().getCity());
    }

    @Test
    @SuppressWarnings("unchecked")
    void mapCollectionBuilder() {
        var personDtos = (List<PersonDto>) BeanGenerator.generateBeanWithGenericTypes(List.class, PersonDto.class);

        var result = beanMapper.mapCollectionBuilder(personDtos, Person.PersonBuilder.class)
                .stream()
                .map(Person.PersonBuilder::build)
                .map(person -> addAddressToPerson(personDtos, person))
                .collect(Collectors.toUnmodifiableList());

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(personDtos.size(), result.size());
        assertInstanceOf(Person.class, result.get(0));
    }

    private Person addAddressToPerson(List<PersonDto> personDtoList, Person person) {
        var predicate = (Predicate<PersonDto>) personDto -> personDto.getName().equals(person.getName()) &&
                personDto.getLastName().equals(person.getLastName());

        var personDto = personDtoList.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);

        var address = beanMapper.mapBuilder(personDto.getAddress(), Address.AddressBuilder.class)
                .build();

        return person.toBuilder()
                .address(address)
                .build();
    }
}