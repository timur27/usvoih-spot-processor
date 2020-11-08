package com.usvoih.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;

    private Double rating;
    private String review;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        return id != null && id.equals(((Rating) o).getId());
    }

    @Override
    public int hashCode() {
        return 34;
    }
}
