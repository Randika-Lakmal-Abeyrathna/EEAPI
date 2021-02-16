package com.randikalakma.eeapi.service.admin;

import com.randikalakma.eeapi.exception.admin.CityException;
import com.randikalakma.eeapi.model.City;
import com.randikalakma.eeapi.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }

    public City findCityById(Integer id){
        return cityRepository.getCityByIdcity(id)
                .orElseThrow(()->new CityException("City by Id "+id+" was not found"));
    }

    public List<City> findCityByName(String city){
       return cityRepository.findByCityIgnoreCaseStartsWith(city);
    }

    public City addCity(City city){
        return cityRepository.save(city);
    }

    public City updateCity(City city){
        return cityRepository.save(city);
    }

    public void deleteCity(Integer id){
        cityRepository.deleteCityByIdcity(id);
    }

}
