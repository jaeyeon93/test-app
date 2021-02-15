package me.jimmy.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Getter
public class Address {

    public Address() {}

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    private String city;
    private String street;
    private String zipcode;
}
