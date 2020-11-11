package com.usvoih.persistence;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usvoih.config.SpotTestConfiguration;
import com.usvoih.dto.SpotDetailsDto;
import com.usvoih.processing.ex.SpotValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
public class SpotCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper jsonMapper;

    @BeforeEach
    public void setUp() {
        jsonMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void contextLoads() {}

    @Test
    @Disabled
    public void should_add_new_spot_with_unapproved_status() throws Exception {
        SpotDetailsDto req = SpotTestConfiguration.createTestSpot();
        String json = jsonMapper.writeValueAsString(req);

        MvcResult result = this.mockMvc.perform(post("/spots")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        SpotDetailsDto res = jsonMapper.readValue(result.getResponse().getContentAsString(), SpotDetailsDto.class);
        assertEquals(req, res);
    }

    @Test
    public void should_validate_required_fields_and_return_bad_request() throws Exception {
        SpotDetailsDto req = SpotTestConfiguration.createTestSpot();
        req.setName(null);
        req.getAddresses().forEach(addressDto -> addressDto.setCountry(null));
        req.getContact().setPhone(null);

        String json = jsonMapper.writeValueAsString(req);

        MvcResult result = this.mockMvc.perform(post("/spots")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), jsonMapper.writeValueAsString(new SpotValidationError(
                List.of("Spot's country cannot be empty",
                        "Spot's name cannot be empty",
                        "Spot's phone number cannot be empty"))));
    }
}