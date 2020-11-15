package com.usvoih.persistence.repository;

import com.usvoih.persistence.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    Optional<Spot> findByName(String name);
}
