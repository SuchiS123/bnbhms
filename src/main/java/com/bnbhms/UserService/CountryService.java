package com.bnbhms.UserService;

import com.bnbhms.Entity.Country;
import com.bnbhms.PayLoad.CountryDto;
import com.bnbhms.Repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private ModelMapper modelMapper;
    private CountryRepository countryRepository;
    public CountryService(ModelMapper modelMapper, CountryRepository countryRepository)
    {
        this.modelMapper=modelMapper;
        this.countryRepository=countryRepository;
    }


    public CountryDto addCountry(CountryDto countryDto) {
        Country country = mapToEntity(countryDto);
        Country savedCountry = countryRepository.save(country);
        CountryDto countryDto1 = mapToDto(savedCountry);
        return countryDto1;


    }

    public Country mapToEntity(CountryDto countryDto )
    {
        return modelMapper.map(countryDto,Country.class);
    }

    public CountryDto mapToDto(Country country )
    {
        return modelMapper.map(country,CountryDto.class);
    }

}
