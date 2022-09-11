package com.aalzatea.commons.beans.mapper.impl;

import com.aalzatea.commons.beans.mapper.BeanMapper;
import com.aalzatea.commons.beans.mapper.config.LombokBuilderNameTransformer;
import com.aalzatea.commons.beans.mapper.config.LombokBuilderNamingConvention;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

public class BeanMapperImpl implements BeanMapper {

    private final ModelMapper modelMapper;

    private final ModelMapper modelMapperBuilder;

    public BeanMapperImpl() {
        this.modelMapper = buildModelMapper();
        this.modelMapperBuilder = buildModelMapperBuilder();
    }

    @Override
    public <S, T> T map(S source, Class<T> target) {
        return modelMapper.map(source, target);
    }

    @Override
    public <S, T> List<T> mapCollection(List<S> source, Class<T> target) {
        return source.stream()
                .map(element -> modelMapper.map(element, target))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public <S, B> B mapBuilder(S source, Class<B> builderClass) {
        return modelMapperBuilder.map(source, builderClass);
    }

    @Override
    public <S, B> List<B> mapCollectionBuilder(List<S> source, Class<B> builderClass) {
        return source.stream()
                .map(element -> mapBuilder(element, builderClass))
                .collect(Collectors.toUnmodifiableList());
    }

    private ModelMapper buildModelMapper() {
        var mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mapper;
    }

    private ModelMapper buildModelMapperBuilder() {
        var mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);

        return mapper;
    }
}
