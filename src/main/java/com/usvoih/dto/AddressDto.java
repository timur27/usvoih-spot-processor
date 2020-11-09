package com.usvoih.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressDto {
    @NotBlank(message = "Spot's country cannot be empty")
    private String country;
    @NotBlank(message = "Spot's city cannot be empty")
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String postalCode;
}
