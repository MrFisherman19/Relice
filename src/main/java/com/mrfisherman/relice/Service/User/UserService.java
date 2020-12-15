package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Dto.MinimalUserDto;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface UserService extends UserDetailsService {

    void signUpUser(User user);

    void addUser(User user);

    void deleteUserByEmail(String email, Authentication authentication);

    void deleteCurrentUser(Authentication authentication);

    void confirmUser(UserConfirmationToken userConfirmationToken);

    void sendConfirmationEmail(String userEmail, String token);

    Optional<User> findByEmail(String email);

    MinimalUserDto loadUserWithoutPasswordByUsername(String name);

    Set<MinimalUserDto> getUsersByGroup(Authentication authentication, Long id);
}
