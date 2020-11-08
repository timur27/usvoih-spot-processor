package com.usvoih.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Category {
    @JsonProperty("Food")
    FOOD,
    @JsonProperty("Health")
    HEALTH,
    @JsonProperty("Service")
    SERVICE,
    @JsonProperty("Shops")
    SHOPS,
    @JsonProperty("Entertainments")
    ENTERTAINMENTS,
    @JsonProperty("Business")
    BUSINESS,
    @JsonProperty("Education")
    EDUCATION,
    @JsonProperty("Beauty")
    BEAUTY;
}
