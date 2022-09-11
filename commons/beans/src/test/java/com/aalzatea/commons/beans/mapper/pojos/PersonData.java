package com.aalzatea.commons.beans.mapper.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class PersonData {

    private String name;

    private String lastName;

    private Integer age;

    private Date birthDate;

    private Boolean single;

    private Gender gender;

    private AddressData address;
}
