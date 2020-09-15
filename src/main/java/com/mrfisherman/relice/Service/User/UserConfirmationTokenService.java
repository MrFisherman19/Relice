package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import org.springframework.stereotype.Service;

@Service
public interface UserConfirmationTokenService {

    void saveConfirmationToken(UserConfirmationToken confirmationTokenService);

    void deleteConfirmationToken(Long id);

    UserConfirmationToken findConfirmationTokenByToken(String token);
}
