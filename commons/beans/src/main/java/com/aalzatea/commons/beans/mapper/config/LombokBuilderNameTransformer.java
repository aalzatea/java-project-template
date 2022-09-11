package com.aalzatea.commons.beans.mapper.config;

import org.modelmapper.internal.util.Strings;
import org.modelmapper.spi.NameTransformer;
import org.modelmapper.spi.NameableType;

/**
 * This implementation has been taken from https://stackoverflow.com/questions/46530323/java-object-mapping-framework-working-with-builder-pattern
 */
public class LombokBuilderNameTransformer implements NameTransformer {

    public static final LombokBuilderNameTransformer INSTANCE = new LombokBuilderNameTransformer();

    @Override
    public String transform(final String name, final NameableType nameableType) {
        return Strings.decapitalize(name);
    }

    @Override
    public String toString() {
        return "Lombok @Builder Mutator";
    }
}
