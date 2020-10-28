package com.usvoih.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Spot {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "spot",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Type type;

    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;

    private String coverPhoto;

    private String photos;

    @OneToMany(mappedBy = "spot",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<BusinessHour> businessHours = new ArrayList<>();

    @OneToMany(
            mappedBy = "spot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Rating> ratings = new ArrayList<>();

    public void setAddresses(List<Address> addresses) {
        addresses.forEach(address -> address.setSpot(this));
        this.addresses = addresses;
    }

    public void setBusinessHours(List<BusinessHour> businessHours) {
        businessHours.forEach(businessHour -> businessHour.setSpot(this));
        this.businessHours = businessHours;
    }

    public void setRatings(List<Rating> ratings) {
        ratings.forEach(rating -> rating.setSpot(this));
        this.ratings = ratings; 
    }
}