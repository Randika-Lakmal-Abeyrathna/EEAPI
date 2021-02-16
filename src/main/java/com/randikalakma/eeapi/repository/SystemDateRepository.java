package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.SystemDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemDateRepository extends JpaRepository<SystemDate,Integer> {

    Optional<SystemDate> getSystemDateByidsystemDate(Integer id);

    void deleteSystemDateByidsystemDate(Integer id);
}
