package com.usvoih.persistence.validation;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeProcessor implements UniqueEntryProcessor {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public void processUniqueEntry(Spot spot) {
        typeRepository.findByCategoryAndSubcategory(spot.getTypeCategory(), spot.getTypeSubcategory())
                .ifPresentOrElse(
                        spot::setType,
                        () -> typeRepository.save(spot.getType())
                );
    }
}