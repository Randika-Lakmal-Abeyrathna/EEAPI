package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Salutation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalutationRepository extends JpaRepository<Salutation,Integer> {
}
