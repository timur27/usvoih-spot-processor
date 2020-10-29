package com.usvoih.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpotDetailsDto {
    private String name;
    private String description;
    private List<AddressDto> addresses;
    private TypeDto type;
    private ContactDto contact;
    private String coverPhoto;
    private String photos;
    private List<BusinessHourDto> businessHours;
    private List<RatingDto> ratings;
}