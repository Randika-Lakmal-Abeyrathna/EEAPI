package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData,Integer> {
}
