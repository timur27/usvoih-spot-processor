package com.usvoih.processing;

import com.usvoih.dto.Unique;
import com.usvoih.persistence.UniqueProcessorMap;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;

@Service
public class SpotService {

    @Autowired
    private SpotRepository spotRepository;

    public Spot save(Spot spot) {
        cascadeSaveUniqueChildren(spot);
        return spotRepository.save(spot);
    }

    private void cascadeSaveUniqueChildren(Spot spot) {
        requireNonNull(spot);
        Arrays.stream(spot.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Unique.class))
                .forEach(field -> UniqueProcessorMap.processorMap.get(getFieldType(field)).processAndSaveUniqueEntry(spot));
    }

    private Class<?> getFieldType(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
            return (Class<?>) stringListType.getActualTypeArguments()[0];
        } else {
            return field.getType();
        }
    }
}