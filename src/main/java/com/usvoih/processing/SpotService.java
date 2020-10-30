package com.usvoih.processing;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.domain.Type;
import com.usvoih.persistence.repository.SpotRepository;
import com.usvoih.persistence.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpotService {

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private TypeRepository typeRepository;

    public Spot save(Spot spot) {
        cascadeSaveChildren(spot);
        return spotRepository.save(spot);
    }

    private void cascadeSaveChildren(Spot spot) {
        Optional<Type> type = typeRepository.findByCategoryAndSubcategory(spot.getType().getCategory(), spot.getType().getSubcategory());
        type.ifPresentOrElse(duplicated -> {
            typeRepository.save(duplicated);
            spot.setType(duplicated);
        }, () -> typeRepository.save(spot.getType()));
    }
}
