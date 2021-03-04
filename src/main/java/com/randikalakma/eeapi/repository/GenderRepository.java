package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.City;
import com.randikalakma.eeapi.model.Gender;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender,Integer> {

    Optional<Gender> getGenderByIdgender(Integer id);

    Optional<Gender> findGenderByGender(String gender);

    Integer countByGender(String gender);

    void deleteGenderByIdgender(Integer id);


}
