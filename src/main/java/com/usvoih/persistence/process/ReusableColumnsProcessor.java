package com.usvoih.persistence.process;

public interface ReusableColumnsProcessor<T> {
    void processAndSaveUniqueEntry(T object);
}