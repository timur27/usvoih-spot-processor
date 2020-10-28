package com.usvoih.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String country;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String postalCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        return id != null && id.equals(((Address) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
