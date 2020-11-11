package com.usvoih.persistence.repository;

import com.usvoih.persistence.domain.BusinessHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.Optional;

public interface BusinessHourRepository extends JpaRepository<BusinessHour, Long> {
    @Query(value = "SELECT bh FROM BusinessHour bh JOIN FETCH bh.spots where bh.open = :open and bh.close = :close")
    Optional<BusinessHour> findByOpenAndClose(@Param("open") LocalTime open, @Param("close") LocalTime close);
}
