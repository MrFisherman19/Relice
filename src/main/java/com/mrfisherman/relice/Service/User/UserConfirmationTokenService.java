package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserConfirmationTokenService {

    void saveConfirmationToken(UserConfirmationToken confirmationTokenService);

    void deleteConfirmationToken(Long id);

    Optional<UserConfirmationToken> findConfirmationTokenByToken(String token);
}
