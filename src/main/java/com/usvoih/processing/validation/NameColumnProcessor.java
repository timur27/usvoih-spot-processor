package com.usvoih.processing.validation;

import com.usvoih.persistence.repository.SpotRepository;
import com.usvoih.processing.ex.ExceptionMessages;
import com.usvoih.processing.ex.SpotNameAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class NameColumnProcessor implements UniqueColumnProcessor<String> {

    private final SpotRepository spotRepository;

    public NameColumnProcessor(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public void process(String spotName) {
        this.spotRepository.findByName(spotName)
                .ifPresent(spot -> {throw new SpotNameAlreadyExistsException(ExceptionMessages.NAME_EXISTS);});
    }
}
