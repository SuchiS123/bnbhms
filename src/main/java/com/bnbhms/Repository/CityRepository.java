package com.bnbhms.Repository;

import com.bnbhms.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCityName(String cityName);
}