package com.test.pims.common;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Обертка для ModelMapper выполняет стандартные преобразования
 * <p>
 * Created by SafinaAA on 27.05.2022
 */
@Component
public class Mapper {
    private final ModelMapper instance;

    public Mapper(ModelMapper mapper) {
        this.instance = mapper;
    }

    /**
     * Одиночное преоборазование по классу
     */
    public <S, T> T map(S source, Class<T> targetClass) {
        return instance.map(source, targetClass);
    }

    /**
     * Преобразование Списка
     */
    public <S, T> Collection<T> mapList(Collection<S> source, Class<T> targetClass) {
        return source.stream().map(t -> instance.map(t, targetClass)).collect(Collectors.toList());
    }
}

