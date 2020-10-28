package com.usvoih.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class AddressDto {
    private String country;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String postalCode;
}
