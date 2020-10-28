package com.usvoih.dto;

import lombok.Data;

@Data
public class BusinessHourDto {
    private Day day;
    private String open;
    private String close;
}
