package com.bnbhms.Controller;

import com.bnbhms.PayLoad.PropertyDto;
import com.bnbhms.PayLoad.UserDto;
import com.bnbhms.UserService.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;
    public PropertyController(PropertyService propertyService)
    {
        this.propertyService = propertyService;
    }

    @PostMapping("/add/{country}/{city}")
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto,
                                      @PathVariable String country, @PathVariable String city)
    {
        PropertyDto propertyDto1 = propertyService.addProperty(propertyDto, country, city);
        return new ResponseEntity<>(propertyDto1, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable  Long id)
    {
        propertyService.deleteById(id);
        return new ResponseEntity<>("Property Deleted SUccesfully",HttpStatus.OK);

    }

    @DeleteMapping("/delete-by-name/{property}")
   public ResponseEntity<?> deletePropertyByName(@PathVariable String property)
    {
        propertyService.deleteByPropertyName(property);
        return new ResponseEntity<>("Property Deleted SUccesfully",HttpStatus.OK);
    }

    @DeleteMapping("/DeleteByCityId/{cityId}")
     public ResponseEntity<String> deleteByCityId(@PathVariable Long cityId)
    {
        propertyService.deleteByCityId(cityId);
        return new ResponseEntity<>("Property Deleted SUccesfully", HttpStatus.OK);
    }


    @DeleteMapping("/DeleteByCityIdFindById/cityId/{CityIdNo}")
    public ResponseEntity<String> deleteByCityIdFindById(@PathVariable Long CityIdNo)
    {
        propertyService.deleteByCityIdFindById(CityIdNo);
        return new ResponseEntity<>("Property Deleted SUccesfully", HttpStatus.OK);
    }





    @PutMapping("/update/properties")
    public ResponseEntity<PropertyDto> updateUser(@RequestBody PropertyDto propertyDto,
                                              @RequestParam Long id)
    {
        PropertyDto propertyDto1 = propertyService.updateUser(propertyDto, id);
        return new ResponseEntity<>(propertyDto1, HttpStatus.OK);
    }

    @PutMapping("/updateByCity/{city}/{country}")
    public ResponseEntity<PropertyDto> updateByCity(@RequestBody PropertyDto propertyDto,
                                                   @PathVariable String city, @PathVariable String country)
    {
        PropertyDto propertyDto1 = propertyService.updateByCity(propertyDto, city,country);
        return new ResponseEntity<>(propertyDto1,HttpStatus.OK);
    }





}
