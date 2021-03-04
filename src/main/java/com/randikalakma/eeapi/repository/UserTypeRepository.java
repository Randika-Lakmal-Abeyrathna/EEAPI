package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Integer> {

    Optional<UserType> findUserTypeByUserType(String userType);
}
