package com.usvoih.processing;

import com.usvoih.dto.Unique;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.process.ReusableColumnsProcessorMap;
import com.usvoih.persistence.repository.SpotRepository;
import com.usvoih.processing.validation.Validator;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;

@Service
public class SpotService {

    private final SpotRepository spotRepository;
    private final Validator validator;

    public SpotService(SpotRepository spotRepository, Validator validator) {
        this.spotRepository = spotRepository;
        this.validator = validator;
    }

    public Spot validateAndSave(Spot spot) {
        validator.validate(spot);
        cascadeSaveUniqueChildren(spot);
        return spotRepository.save(spot);
    }

    private void cascadeSaveUniqueChildren(Spot spot) {
        requireNonNull(spot);
        Arrays.stream(spot.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Unique.class))
                .forEach(field -> ReusableColumnsProcessorMap.processorMap.get(getFieldType(field)).processAndSaveUniqueEntry(spot));
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