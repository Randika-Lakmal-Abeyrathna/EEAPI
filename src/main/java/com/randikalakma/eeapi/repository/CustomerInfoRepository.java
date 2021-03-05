package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.CustomerInfo;
import com.randikalakma.eeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo,Integer> {

    Optional<CustomerInfo> findByUser(User user);
}
