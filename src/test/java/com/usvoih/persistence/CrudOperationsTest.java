package com.usvoih.persistence;

import com.usvoih.config.LocalPersistenceConfig;
import com.usvoih.config.ModelMapperConfig;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// TODO add tests before implementing extended spot
@SpringBootTest(classes = SpotRepository.class)
@EnableJpaRepositories
@Import({LocalPersistenceConfig.class, ModelMapperConfig.class})
public class CrudOperationsTest {

    @Autowired
    private SpotRepository spotRepository;

    @AfterEach
    public void tearDown() {
        spotRepository.deleteAll();
    }

    @Test
    public void should_persist_spot_successfully () {
        spotRepository.save(new Spot());

        List<Spot> spots = spotRepository.findAll();

        assertThat(spots).isNotEmpty();
        assertThat(spots).hasSize(1);
    }

    @Test
    public void should_remove_spot_successfully () {
        Spot spot = new Spot();
        spotRepository.save(spot);

        Optional<Spot> spotToDelete = spotRepository.findById(spot.getId());
        spotToDelete.ifPresent(spotRepository::delete);

        assertThat(spotRepository.findAll()).isEmpty();
    }

    @Test
    public void should_update_existing_spot() {
        String updatedName = "updated name";
        spotRepository.save(new Spot());

        Optional<Spot> updated = spotRepository.findAll().stream().findFirst();
        updated.ifPresentOrElse(s -> {
                        s.setName(updatedName);
                        spotRepository.save(s);
                }, () -> {throw new NoSuchElementException();});

        List<Spot> spots = spotRepository.findAll();
        assertThat(spots).isNotEmpty();
        assertThat(spots).hasSize(1);
        assertNotNull(spots.get(0).getName());
        assertEquals(spots.get(0).getName(), updatedName);
    }
}