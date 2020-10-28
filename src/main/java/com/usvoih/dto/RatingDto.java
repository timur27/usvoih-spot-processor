package com.usvoih.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class RatingDto {
    private Double rating;
    private String review;
    private LocalDate date;
}
