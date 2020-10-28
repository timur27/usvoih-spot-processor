package com.usvoih.persistence.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue
    private Long id;

    private int phone;
    private String email;
    private String website;
    private String instagram;
    private String facebook ;
    private String vk;
}
