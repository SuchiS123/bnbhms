package com.bnbhms.PayLoad;

import com.bnbhms.Entity.City;
import com.bnbhms.Entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private Long id;
    private String propertyName;
    private Integer noOfBedroom;
    private Integer noOfBathroom;
    private City city;
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getNoOfBedroom() {
        return noOfBedroom;
    }

    public void setNoOfBedroom(Integer noOfBedroom) {
        this.noOfBedroom = noOfBedroom;
    }

    public Integer getNoOfBathroom() {
        return noOfBathroom;
    }

    public void setNoOfBathroom(Integer noOfBathroom) {
        this.noOfBathroom = noOfBathroom;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
