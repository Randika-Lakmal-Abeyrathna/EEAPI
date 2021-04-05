package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.BrandImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandImageRepository extends JpaRepository<BrandImage,Integer>{
}
