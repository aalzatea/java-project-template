package com.aalzatea.commons.beans.mapper.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@ToString
public class Person {

    private final String name;

    private final String lastName;

    private final Integer age;

    private final Date birthDate;

    private final Boolean single;

    private final Gender gender;

    private final Address address;
}
