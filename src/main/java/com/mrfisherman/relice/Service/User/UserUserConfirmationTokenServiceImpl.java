package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import com.mrfisherman.relice.Repository.UserConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUserConfirmationTokenServiceImpl implements UserConfirmationTokenService {

    private final UserConfirmationTokenRepository userConfirmationTokenRepository;

    @Autowired
    public UserUserConfirmationTokenServiceImpl(UserConfirmationTokenRepository userConfirmationTokenRepository) {
        this.userConfirmationTokenRepository = userConfirmationTokenRepository;
    }

    @Override
    public void saveConfirmationToken(UserConfirmationToken userConfirmationToken) {
        userConfirmationTokenRepository.save(userConfirmationToken);
    }

    @Override
    public void deleteConfirmationToken(Long id) {
        userConfirmationTokenRepository.deleteById(id);
    }

    @Override
    public UserConfirmationToken findConfirmationTokenByToken(String token) {
        return userConfirmationTokenRepository.findUserConfirmationTokenByConfirmationToken(token);
    }
}
