package com.usvoih.rest;

import com.usvoih.dto.SpotDetailsDto;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.processing.SpotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/spots")
public class SpotCommandController {

    @Autowired
    private SpotService spotService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public Spot addSpot(@Valid @RequestBody SpotDetailsDto spotDetailsDto) {
        Spot spot = modelMapper.map(spotDetailsDto, Spot.class);
        return spotService.validateAndSave(spot);
    }
}