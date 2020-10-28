package com.usvoih.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usvoih.dto.Day;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class BusinessHour {
    @Id
    @GeneratedValue
    private Long id;

    private Day day;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime open;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime close;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessHour)) return false;
        return id != null && id.equals(((BusinessHour) o).getId());
    }

    @Override
    public int hashCode() {
        return 32;
    }
}
