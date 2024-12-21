package com.bnbhms.UserService;

import com.bnbhms.Entity.City;
import com.bnbhms.PayLoad.CityDto;
import com.bnbhms.Repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityRepository cityRepository;
    private ModelMapper modelMapper;
    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }
    public CityDto addCity(CityDto cityDto) {
        City city = mapToEntity(cityDto);
        City savedCity = cityRepository.save(city);
        CityDto cityDto1 = mapToDto(savedCity);
        return cityDto1;

    }

    public City mapToEntity(CityDto cityDto) {
       return modelMapper.map(cityDto,City.class);
    }

    public CityDto mapToDto(City city) {
        return modelMapper.map(city,CityDto.class);
    }

}
