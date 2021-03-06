package com.usvoih.persistence.process;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.TypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeProcessor implements ReusableColumnsProcessor<Spot> {

    private final TypeRepository typeRepository;

    public TypeProcessor(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void processAndSaveUniqueEntry(Spot spot) {
        typeRepository.findByCategoryAndSubcategory(spot.getTypeCategory(), spot.getTypeSubcategory())
                .ifPresentOrElse(spot::setType, () -> typeRepository.save(spot.getType()));
    }
}