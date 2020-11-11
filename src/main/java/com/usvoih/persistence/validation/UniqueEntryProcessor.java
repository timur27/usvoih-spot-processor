package com.usvoih.persistence.validation;

public interface UniqueEntryProcessor<T> {
    void processAndSaveUniqueEntry(T object);
}
