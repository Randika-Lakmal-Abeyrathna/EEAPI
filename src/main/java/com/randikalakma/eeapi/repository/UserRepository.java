package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Status;
import com.randikalakma.eeapi.model.User;
import com.randikalakma.eeapi.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmailAndUserType(String email, UserType userType);
}
