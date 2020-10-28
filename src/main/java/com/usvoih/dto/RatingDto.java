package com.usvoih.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class RatingDto {
    private Double rating;
    private String review;
    private String date;
}
