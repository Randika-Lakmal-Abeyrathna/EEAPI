package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Customer;
import com.randikalakma.eeapi.model.CustomerToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerTokenRepository extends JpaRepository<CustomerToken,Integer> {

    Optional<CustomerToken> findByToken(String token);

    void deleteCustomerTokenByTokenAndCustomer(String token, Customer customer);

}
