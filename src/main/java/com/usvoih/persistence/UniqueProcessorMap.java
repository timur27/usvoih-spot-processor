package com.usvoih.persistence;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.domain.Type;
import com.usvoih.persistence.validation.TypeProcessor;
import com.usvoih.persistence.validation.UniqueEntryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UniqueProcessorMap {

    @Autowired
    private TypeProcessor typeProcessor;
    public static Map<Object, UniqueEntryProcessor<Spot>> processorMap = new HashMap<>();


    @PostConstruct
    public void init() {
        processorMap.put(Type.class, typeProcessor);
    }
}
