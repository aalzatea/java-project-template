package com.aalzatea.commons.beans.mapper.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Address {

    private final String address;

    private final String city;

    private final String state;

    private final String country;
}
