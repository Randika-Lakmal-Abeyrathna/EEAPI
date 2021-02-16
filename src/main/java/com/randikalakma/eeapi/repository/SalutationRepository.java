package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Salutation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalutationRepository extends JpaRepository<Salutation,Integer> {

    Optional<Salutation> getSalutationByIdsalutation(Integer id);

    void deleteCityByIdsalutation(Integer id);
}
