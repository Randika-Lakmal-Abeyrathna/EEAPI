package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,String> {
}
