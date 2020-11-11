package com.usvoih.persistence;

import com.usvoih.persistence.domain.BusinessHour;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.domain.Type;
import com.usvoih.persistence.validation.BusinessHourProcessor;
import com.usvoih.persistence.validation.TypeProcessor;
import com.usvoih.persistence.validation.UniqueEntryProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UniqueProcessorMap {

    private final TypeProcessor typeProcessor;
    private final BusinessHourProcessor businessHourProcessor;

    public static Map<Object, UniqueEntryProcessor<Spot>> processorMap = new HashMap<>();

    public UniqueProcessorMap(TypeProcessor typeProcessor, BusinessHourProcessor businessHourProcessor) {
        this.typeProcessor = typeProcessor;
        this.businessHourProcessor = businessHourProcessor;
    }

    @PostConstruct
    public void init() {
        processorMap.put(Type.class, typeProcessor);
        processorMap.put(BusinessHour.class, businessHourProcessor);
    }
}
