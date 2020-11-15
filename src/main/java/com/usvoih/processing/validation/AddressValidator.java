package com.usvoih.processing.validation;

import com.usvoih.persistence.domain.Spot;
import com.usvoih.persistence.repository.AddressRepository;
import com.usvoih.processing.ex.AddressAlreadyExistsException;
import com.usvoih.processing.ex.ExceptionMessages;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressValidator extends Validator {
    @Autowired
    private AddressRepository addressRepository;

    public AddressValidator(Validator validator) {
        super(validator);
    }

    @Override
    public void validate(Spot spot) {
        spot.getAddresses().forEach(address -> {
            this.addressRepository.findByCityAndStreetAndHouseAndApartment(address.getCity(), address.getStreet(), address.getHouse(), address.getApartment())
                    .ifPresent(adrs -> {throw new AddressAlreadyExistsException(ExceptionMessages.ADDRESS_EXISTS);});
            this.checkNext(spot);
        });
    }
}
