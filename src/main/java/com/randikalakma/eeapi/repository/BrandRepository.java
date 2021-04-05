package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {

    Optional<Brand> findBrandByBrandname(String brandName);

    int countByBrandname(String brandname);

}

