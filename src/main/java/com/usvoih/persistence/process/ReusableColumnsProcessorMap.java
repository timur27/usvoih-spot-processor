package com.usvoih.persistence.process;

import com.usvoih.persistence.domain.BusinessHour;
import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.domain.Type;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReusableColumnsProcessorMap {

    private final TypeProcessor typeProcessor;
    private final BusinessHourProcessor businessHourProcessor;

    public static Map<Object, ReusableColumnsProcessor<Spot>> processorMap = new HashMap<>();

    public ReusableColumnsProcessorMap(TypeProcessor typeProcessor, BusinessHourProcessor businessHourProcessor) {
        this.typeProcessor = typeProcessor;
        this.businessHourProcessor = businessHourProcessor;
    }

    @PostConstruct
    public void init() {
        processorMap.put(Type.class, typeProcessor);
        processorMap.put(BusinessHour.class, businessHourProcessor);
    }
}
