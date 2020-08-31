package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByEmail(String email);

    void singUpUser(User user);

    void confirmUser(UserConfirmationToken userConfirmationToken);

    void sendConfirmationEmail(String userEmail, String token);
}
