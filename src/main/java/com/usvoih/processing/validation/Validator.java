package com.usvoih.processing.validation;

import com.usvoih.persistence.domain.Spot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Service
public abstract class Validator {
    public Validator next;
    public abstract void validate(Spot spot);

    protected void checkNext(Spot spot) {
        if (next == null) {
            return;
        }
        next.validate(spot);
    }
}
