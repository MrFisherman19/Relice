package com.mrfisherman.relice.Entity.Property;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String streetName;
    private String numberOnStreet;
    private String zipCode;
    private String city;
    private String country;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumberOnStreet() {
        return numberOnStreet;
    }

    public void setNumberOnStreet(String numberOnStreet) {
        this.numberOnStreet = numberOnStreet;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
