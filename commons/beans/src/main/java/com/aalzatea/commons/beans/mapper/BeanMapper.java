package com.aalzatea.commons.beans.mapper;

import java.util.List;

public interface BeanMapper {

    <S, T> T map(S source, Class<T> target);

    <S, T> List<T> mapCollection(List<S> source, Class<T> target);

    <S, B> B mapBuilder(S source, Class<B> builderClass);

    <S, B> List<B> mapCollectionBuilder(List<S> source, Class<B> builderClass);
}
