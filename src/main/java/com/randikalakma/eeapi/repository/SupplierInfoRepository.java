package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.SupplierInfo;
import com.randikalakma.eeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierInfoRepository extends JpaRepository<SupplierInfo,Integer> {

    Optional<SupplierInfo> findByUser(User user);
}
