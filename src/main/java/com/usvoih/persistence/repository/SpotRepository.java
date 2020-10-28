package com.usvoih.persistence.repository;

import com.usvoih.persistence.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {

}
