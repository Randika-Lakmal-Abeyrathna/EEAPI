package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    Optional<City> getCityByIdcity(Integer id);

    List<City> findByCityIgnoreCaseContaining(String city);

    List<City> findByCityIgnoreCaseStartsWith(String city);

    List<City> findCityByCity(String city);

    void deleteCityByIdcity(Integer id);
}
