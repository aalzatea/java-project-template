package com.aalzatea.commons.beans.mapper.config;

import org.modelmapper.spi.NamingConvention;
import org.modelmapper.spi.PropertyType;

/**
 * This implementation has been taken from https://stackoverflow.com/questions/46530323/java-object-mapping-framework-working-with-builder-pattern
 */
public class LombokBuilderNamingConvention implements NamingConvention {

    public static final LombokBuilderNamingConvention INSTANCE = new LombokBuilderNamingConvention();

    @Override
    public boolean applies(String propertyName, PropertyType propertyType) {
        return PropertyType.METHOD.equals(propertyType);
    }

    @Override
    public String toString() {
        return "Lombok @Builder Naming Convention";
    }
}
