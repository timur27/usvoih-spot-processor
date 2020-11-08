package com.usvoih.persistence.validation;

import com.usvoih.persistence.domain.Spot;

public interface UniqueEntryProcessor {
    void processUniqueEntry(Spot spot);
}
