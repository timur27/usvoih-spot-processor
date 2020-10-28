package com.usvoih.persistence;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usvoih.config.SpotTestConfiguration;
import com.usvoih.dto.SpotDetailsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
    public void should_add_new_spot_with_unapproved_status() throws Exception {
        SpotDetailsDto request = SpotTestConfiguration.createTestSpot();
        String json = jsonMapper.writeValueAsString(request);

        MvcResult result = this.mockMvc.perform(post("/spots")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        SpotDetailsDto response = jsonMapper.readValue(result.getResponse().getContentAsString(), SpotDetailsDto.class);
        assertEquals(request, response);
    }
}