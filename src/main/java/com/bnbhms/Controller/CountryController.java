package com.bnbhms.Controller;

import com.bnbhms.PayLoad.CountryDto;
import com.bnbhms.UserService.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private CountryService countryService;
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/add/country")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto)
    {
        CountryDto countryDto1 = countryService.addCountry(countryDto);
        return new ResponseEntity<>(countryDto1, HttpStatus.OK);

    }
}
