package com.usvoih.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Day {
    @JsonProperty("Monday")
    MONDAY,
    @JsonProperty("Tuesday")
    TUESDAY,
    @JsonProperty("Wednesday")
    WEDNESDAY,
    @JsonProperty("Thursday")
    THURSDAY,
    @JsonProperty("Friday")
    FRIDAY,
    @JsonProperty("Saturday")
    SATURDAY,
    @JsonProperty("Sunday")
    SUNDAY;
}
