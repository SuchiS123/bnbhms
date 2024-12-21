package com.bnbhms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Property {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(name = "property_name", nullable = false)
  private String propertyName;

  @Column(name = "no_of_bedroom", nullable = false)
  private Integer noOfBedroom;

  @Column(name = "no_of_bathroom", nullable = false)
  private Integer noOfBathroom;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToOne(cascade =CascadeType.ALL)
  @JoinColumn(name = "country_id")
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