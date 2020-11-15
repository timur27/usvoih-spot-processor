package com.usvoih.processing.validation;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import com.usvoih.processing.ex.ExceptionMessages;
import com.usvoih.processing.ex.SpotNameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;

public class NameValidator extends Validator {

    @Autowired
    private SpotRepository spotRepository;

    public NameValidator(Validator next) {
        super(next);
    }

    @Override
    public void validate(Spot spot) {
        this.spotRepository.findByName(spot.getName())
                .ifPresent(name -> {throw new SpotNameAlreadyExistsException(ExceptionMessages.NAME_EXISTS);});
        this.checkNext(spot);
    }
}
