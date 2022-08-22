package com.aalzatea.commons.test.beans.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class Person {

    private String name;

    private String lastName;

    private Integer age;

    private Date birthDate;

    private Boolean single;

    private Gender gender;
}
