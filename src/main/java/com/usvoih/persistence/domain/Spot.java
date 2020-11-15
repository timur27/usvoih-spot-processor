package com.usvoih.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usvoih.dto.Category;
import com.usvoih.dto.Unique;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Spot {
    @Id
    @Column(name = "spot_id")
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "spot",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @Unique
    @ManyToOne
    private Type type;

    @OneToOne(cascade = {CascadeType.ALL})
    private Contact contact;

    private String coverPhoto;

    private String photos;

    @Unique
    @ManyToMany
    @JoinTable(name = "spot_hours",
                joinColumns = @JoinColumn(name = "spot_id"),
                inverseJoinColumns = @JoinColumn(name = "business_hour_id"))
    private Set<BusinessHour> businessHours = new HashSet<>();

    @OneToMany(
            mappedBy = "spot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Rating> ratings = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.UNAPPROVED;

    public void setAddresses(List<Address> addresses) {
        addresses.forEach(address -> address.setSpot(this));
        this.addresses = addresses;
    }

    public void addBusinessHour(BusinessHour businessHour) {
        businessHours.add(businessHour);
        businessHour.getSpots().add(this);
    }

    public void removeBusinessHour(BusinessHour businessHour) {
        businessHours.remove(businessHour);
        businessHour.getSpots().remove(this);
    }

    public void setRatings(List<Rating> ratings) {
        ratings.forEach(rating -> rating.setSpot(this));
        this.ratings = ratings; 
    }

    @JsonIgnore
    public Category getTypeCategory() {
        return this.getType().getCategory();
    }

    @JsonIgnore
    public String getTypeSubcategory() {
        return this.getType().getSubcategory();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Spot)) return false;
        return id != null && id.equals(((Spot) obj).getId());
    }

    @Override
    public int hashCode() {
        return 33;
    }
}