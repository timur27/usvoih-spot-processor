package com.usvoih.persistence.repository;

import com.usvoih.persistence.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCityAndStreetAndHouseAndApartment(String city, String street, String house, String apartment);
}
