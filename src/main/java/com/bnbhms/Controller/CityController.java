package com.bnbhms.Controller;


import com.bnbhms.PayLoad.CityDto;
import com.bnbhms.UserService.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private CityService cityService;
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add/city")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDto)
    {
        CityDto cityDto1 = cityService.addCity(cityDto);
        return new ResponseEntity<>(cityDto1, HttpStatus.OK);
    }
}
