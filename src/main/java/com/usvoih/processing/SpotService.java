package com.usvoih.processing;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotService {

    @Autowired
    private SpotRepository spotRepository;

    public Spot save(Spot spot) {
        return spotRepository.save(spot);
    }
}
