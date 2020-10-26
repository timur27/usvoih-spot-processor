package com.usvoih.persistence;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SpotRepository.class)
public class CrudOperationsTest {

    @Autowired
    private SpotRepository spotRepository;

    @Test
    public void should_persist_spot_successfully () {
        // given one persisted spot
        Spot spot = prepareSpot();
        spotRepository.save(spot);

        // when trying to find all persisted spots
        List<Spot> spots = spotRepository.findAll();

        // then there is only one spot returned
        assertEquals(spots.size(), 1);
    }

    private Spot prepareSpot() {
        return new Spot();
    }
}
