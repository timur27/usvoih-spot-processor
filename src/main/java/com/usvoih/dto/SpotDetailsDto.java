package com.usvoih.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class SpotDetailsDto {
    @NotBlank(message = "Spot's name cannot be empty")
    private String name;
    private String description;
    @Valid
    private List<AddressDto> addresses;
    private TypeDto type;
    @Valid
    private ContactDto contact;
    private String coverPhoto;
    private String photos;
    private List<BusinessHourDto> businessHours;
    private List<RatingDto> ratings;
}