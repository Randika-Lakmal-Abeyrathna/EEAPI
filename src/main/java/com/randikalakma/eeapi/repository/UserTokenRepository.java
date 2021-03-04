package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.User;
import com.randikalakma.eeapi.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken,Integer> {

    Optional<UserToken> findByToken(String token);

    void deleteUserTokenByTokenAndUser(String token, User user);
}
