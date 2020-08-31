package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserConfirmationTokenRepository extends JpaRepository<UserConfirmationToken, Long> {
    Optional<UserConfirmationToken> findUserConfirmationTokenByConfirmationToken(String token);
}
