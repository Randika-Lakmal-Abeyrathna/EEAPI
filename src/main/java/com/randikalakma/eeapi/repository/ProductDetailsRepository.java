package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails,Integer> {

    Optional<ProductDetails> findProductDetailsById(Integer id);
}
