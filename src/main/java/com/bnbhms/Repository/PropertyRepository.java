package com.bnbhms.Repository;

import com.bnbhms.Entity.City;
import com.bnbhms.Entity.Country;
import com.bnbhms.Entity.Property;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByPropertyName(String propertyName);
    List<Property> findByCity_Id(Long cityId);
    Optional<Property> findByCityId(Long cityId);



}