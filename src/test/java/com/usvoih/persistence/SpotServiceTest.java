package com.usvoih.persistence;

import com.usvoih.config.SpotTestConfiguration;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import com.usvoih.persistence.repository.TypeRepository;
import com.usvoih.processing.SpotService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpotServiceTest {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SpotService spotService;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Test
    @Disabled
    public void should_add_new_spot_with_existing_type() {
        Spot spot = this.modelMapper.map(SpotTestConfiguration.createTestSpot(), Spot.class);
        Spot duplicatedSpot = this.modelMapper.map(SpotTestConfiguration.createTestSpot(), Spot.class);
        this.spotService.save(spot);

        assertThat(this.spotRepository.findAll()).hasSize(1);
        assertThat(this.typeRepository.findAll()).hasSize(1);
        this.spotService.save(duplicatedSpot);
        assertThat(this.spotRepository.findAll()).hasSize(2);
        assertThat(this.typeRepository.findAll()).hasSize(1);
    }

    @Test
    @Disabled
    public void should_add_new_spot_with_existing_business_hours() {

    }
}
