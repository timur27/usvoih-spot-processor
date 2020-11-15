package com.usvoih.persistence.process;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.BusinessHourRepository;
import org.springframework.stereotype.Service;

@Service
public class BusinessHourProcessor implements ReusableColumnsProcessor<Spot> {

    private final BusinessHourRepository businessHourRepository;

    public BusinessHourProcessor(BusinessHourRepository businessHourRepository) {
        this.businessHourRepository = businessHourRepository;
    }

    @Override
    public void processAndSaveUniqueEntry(Spot spot) {
        spot.getBusinessHours().forEach(businessHour -> businessHourRepository.findByOpenAndClose(businessHour.getOpen(), businessHour.getClose())
                .ifPresentOrElse(existing -> {
                    spot.removeBusinessHour(businessHour);
                    spot.addBusinessHour(existing);
                }, () -> businessHourRepository.save(businessHour)));
    }
}
