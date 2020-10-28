package com.usvoih.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ContactDto {
    private int phone;
    private String email;
    private String website;
    private String instagram;
    private String facebook ;
    private String vk;
}
