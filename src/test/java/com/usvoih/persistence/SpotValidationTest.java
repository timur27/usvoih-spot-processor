package com.usvoih.persistence;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.usvoih.config.SpotTestConfiguration;
import com.usvoih.dto.SpotDetailsDto;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.SpotRepository;
import com.usvoih.processing.SpotService;
import com.usvoih.processing.ex.ExceptionMessages;
import com.usvoih.processing.ex.SpotValidationError;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpotValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpotService spotService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SpotRepository spotRepository;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @AfterEach
    private void tearDown() {
        this.spotRepository.deleteAll();
    }

    @Test
    public void should_validate_name_field_and_return_bad_request() throws Exception {
        SpotDetailsDto spot = SpotTestConfiguration.createTestSpot();
        this.spotService.validateAndSave(this.modelMapper.map(SpotTestConfiguration.createTestSpot(), Spot.class));
        String duplicatedSpot = jsonMapper.writeValueAsString(spot);

        MvcResult result = this.mockMvc.perform(post("/spots")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(duplicatedSpot))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), jsonMapper.writeValueAsString(new SpotValidationError(
                List.of(ExceptionMessages.NAME_EXISTS))));
    }

    @Test
    public void should_validate_address_field_and_return_bad_request() throws Exception {
        SpotDetailsDto spot = SpotTestConfiguration.createTestSpot();
        this.spotService.validateAndSave(this.modelMapper.map(SpotTestConfiguration.createTestSpot(), Spot.class));
        spot.setName("Unique name");
        String duplicatedSpot = jsonMapper.writeValueAsString(spot);

        MvcResult result = this.mockMvc.perform(post("/spots")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(duplicatedSpot))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), jsonMapper.writeValueAsString(new SpotValidationError(
                List.of(ExceptionMessages.ADDRESS_EXISTS))));
    }
}
