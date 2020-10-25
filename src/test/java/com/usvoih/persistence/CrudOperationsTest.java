package com.usvoih.persistence;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SpotRepository.class)
@EnableJpaRepositories(basePackages = "com.usvoih.persistence.repository")
public class CrudOperationsTest {

    @Autowired
    private SpotRepository spotRepository;

    @Test
    void should_persist_spot_succesfully () {
        // given one persisted spot
        Spot spot = prepareSpot();
        spotRepository.save(spot);

        // when trying to find all persisted spots
        List<Spot> spots = spotRepository.findAll();

        // then there is only one spot returned
        assertTrue(spots.size() == 1);
    }

    private Spot prepareSpot() {
        return new Spot();
    }
}
