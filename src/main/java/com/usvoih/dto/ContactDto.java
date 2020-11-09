package com.usvoih.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContactDto {
    @NotBlank(message = "Spot's phone number cannot be empty")
    private String phone;
    private String email;
    private String website;
    private String instagram;
    private String facebook ;
    private String vk;
}
