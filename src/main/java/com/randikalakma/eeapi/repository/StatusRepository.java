package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Salutation;
import com.randikalakma.eeapi.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {


    Optional<Status> getSalutationByIdstatus(Integer id);

    void deleteSalutationByIdstatus(Integer id);

    List<Status> findStatusByStatus(String status);

}
