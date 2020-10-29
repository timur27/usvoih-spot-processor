package com.usvoih.config;

import com.usvoih.dto.BusinessHourDto;
import com.usvoih.dto.RatingDto;
import com.usvoih.persistence.domain.BusinessHour;
import com.usvoih.persistence.domain.Rating;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(RatingDto.class, Rating.class)
                .addMappings(mapper -> mapper.using(localDateConverter())
                        .map(RatingDto::getDate, Rating::setDate));

        modelMapper.typeMap(BusinessHourDto.class, BusinessHour.class)
                .addMappings(mapper -> mapper.using(localTimeConverter())
                        .map(BusinessHourDto::getOpen, BusinessHour::setOpen))
                .addMappings(mapper -> mapper.using(localTimeConverter())
                        .map(BusinessHourDto::getClose, BusinessHour::setClose));

        return modelMapper;
    }

    private Converter<String, LocalDate> localDateConverter() {
        return mappingContext ->
                mappingContext.getSource() == null
                        ? null
                        : LocalDate.parse(mappingContext.getSource());
    }

    private Converter<String, LocalTime> localTimeConverter() {
        return mappingContext ->
                mappingContext.getSource() == null
                        ? null
                        : LocalTime.parse(mappingContext.getSource());
    }
}
