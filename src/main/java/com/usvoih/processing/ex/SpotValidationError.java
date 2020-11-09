package com.usvoih.processing.ex;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class SpotValidationError implements Serializable {
    private List<String> errors;
}
