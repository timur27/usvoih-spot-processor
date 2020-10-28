package com.usvoih.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class TypeDto {
    private Category category;
    private String subcategory;
}
