package com.usvoih.processing;

import com.usvoih.dto.Unique;
import com.usvoih.persistence.UniqueProcessorMap;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

@Service
public class SpotService {

    @Autowired
    private SpotRepository spotRepository;

    public Spot save(Spot spot) {
        cascadeSaveChildren(spot);
        return spotRepository.save(spot);
    }

    private void cascadeSaveChildren(Spot spot) {
        requireNonNull(spot);
        Arrays.stream(spot.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Unique.class))
                .forEach(field -> UniqueProcessorMap.processorMap.get(field.getType()).processUniqueEntry(spot));
    }
}