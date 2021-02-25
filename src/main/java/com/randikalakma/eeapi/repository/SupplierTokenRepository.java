package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Supplier;
import com.randikalakma.eeapi.model.SupplierToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierTokenRepository extends JpaRepository<SupplierToken,Integer> {
    Optional<SupplierToken> findByToken(String token);

    void deleteSupplierTokenByTokenAndSupplier(String token, Supplier supplier);
}
