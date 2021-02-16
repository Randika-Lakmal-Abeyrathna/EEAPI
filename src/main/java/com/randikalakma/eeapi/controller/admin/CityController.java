package com.randikalakma.eeapi.controller.admin;

import com.randikalakma.eeapi.model.City;
import com.randikalakma.eeapi.service.admin.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/city")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAllCities(){

        List<City> cityList = cityService.findAllCities();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Integer id){
        City city= cityService.findCityById(id);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public  ResponseEntity<List<City>> getCityByName(@PathVariable("name") String name){
        List<City> cityList = cityService.findCityByName(name);
        return new ResponseEntity<>(cityList,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<City> addCity(@RequestBody City city){
        City newCity = cityService.addCity(city);
        return new ResponseEntity<>(newCity,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<City> updateCity(@RequestBody City city){
        City newCity = cityService.updateCity(city);
        return new ResponseEntity<>(newCity,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id){
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
