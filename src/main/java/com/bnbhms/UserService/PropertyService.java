package com.bnbhms.UserService;

import com.bnbhms.Entity.City;
import com.bnbhms.Entity.Country;
import com.bnbhms.Entity.Property;
import com.bnbhms.PayLoad.PropertyDto;
import com.bnbhms.Repository.CityRepository;
import com.bnbhms.Repository.CountryRepository;
import com.bnbhms.Repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;
    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    public PropertyService(ModelMapper modelMapper, PropertyRepository propertyRepository,
                           CityRepository cityRepository,CountryRepository countryRepository )
    {
        this.modelMapper=modelMapper;
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    public PropertyDto addProperty(PropertyDto propertyDto, String country, String city) {
        Property property = mapToEntity(propertyDto);

        Country opCountry = countryRepository.findByCountryName(country).orElseThrow(()->
                new RuntimeException("Could not find country"));
        property.setCountry(opCountry);

        City opCity = cityRepository.findByCityName (city).
                orElseThrow(()->new RuntimeException("Could not find city"));

        property.setCity(opCity);
        Property savedProperty = propertyRepository.save(property);
        PropertyDto propertyDto1 = mapToDto(savedProperty);

        return propertyDto1;


    }

    public Property mapToEntity(PropertyDto propertyDto) {

        return modelMapper.map(propertyDto, Property.class);
    }

    public PropertyDto mapToDto(Property property) {

        return modelMapper.map(property, PropertyDto.class);
    }

    public void deleteById(Long id) {

        propertyRepository.deleteById(id);
    }

    public void deleteByPropertyName(String propertyName) {
        Property property = propertyRepository.findByPropertyName(propertyName)
                .orElseThrow(() -> new RuntimeException("Property not found with name: " + propertyName));
        propertyRepository.delete(property);
    }


    public PropertyDto updateUser(PropertyDto propertyDto,Long id) {
        Property property = mapToEntity(propertyDto);
        Optional<Property> opId = propertyRepository.findById(id);
        Property property1 = opId.get();
        property1.setId(id);
        property1.setPropertyName(propertyDto.getPropertyName());
        property1.setNoOfBedroom(propertyDto.getNoOfBedroom());
        property1.setNoOfBathroom(propertyDto.getNoOfBathroom());

        Property updateProperty = propertyRepository.save(property1);
        PropertyDto propertyDto1 = mapToDto(updateProperty);
        return propertyDto1;


    }


    public PropertyDto updateByCity(PropertyDto propertyDto, String city, String country) {

        City findCity = cityRepository.findByCityName(city).orElseThrow(() ->
                new RuntimeException("Could not find City"));


        Property property = mapToEntity(propertyDto);
        property.setId(propertyDto.getId());
        property.setPropertyName(propertyDto.getPropertyName());
        property.setNoOfBedroom(propertyDto.getNoOfBedroom());
        property.setNoOfBathroom(propertyDto.getNoOfBathroom());


        Country findCountry = countryRepository.findByCountryName(country).orElseThrow(() ->
                new RuntimeException("Could not find Country"));

        property.setCountry(findCountry);
        property.setCity(findCity);


        Property updateProperty = propertyRepository.save(property);
        PropertyDto propertyDto1 = mapToDto(updateProperty);
        return propertyDto1;

    }

    public void deleteByCityId(Long city) {

        List<Property> property = propertyRepository.findByCity_Id(city);
        if(!property.isEmpty()) {
            propertyRepository.deleteAll(property);
        }
        else {
            throw new RuntimeException("No properties found for this city");
        }




    }

    public void deleteByCityIdFindById(Long cityIdNo) {
        Property byCityIdProperty = propertyRepository.findByCityId(cityIdNo).orElseThrow(
                ()->new RuntimeException("Could not find by city id " + cityIdNo));

        Long id = byCityIdProperty.getId();
        propertyRepository.deleteById(id);
    }
}

