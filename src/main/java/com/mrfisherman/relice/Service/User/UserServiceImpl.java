package com.mrfisherman.relice.Service.User;


import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import com.mrfisherman.relice.Repository.UserRepository;
import com.mrfisherman.relice.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserConfirmationTokenService userConfirmationTokenService;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConfirmationTokenService userConfirmationTokenService, EmailService emailService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConfirmationTokenService = userConfirmationTokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {
            return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

    @Override
    public void singUpUser(User user) {
        final String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        final User createdUser = userRepository.save(user);

        final UserConfirmationToken confirmationToken = new UserConfirmationToken(createdUser);

        userConfirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    @Override
    public void confirmUser(UserConfirmationToken userConfirmationToken) {
        final User user = userConfirmationToken.getUser();

        user.setEnabled(true);

        userRepository.save(user);

        userConfirmationTokenService.deleteConfirmationToken(userConfirmationToken.getConfirmationTokenId());
    }

    @Override
    public void sendConfirmationEmail(String userEmail, String token) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userEmail);
        simpleMailMessage.setSubject("Welcome to Relice! Please confirm your email address!");
        simpleMailMessage.setFrom("<MAIL>");
        simpleMailMessage.setText("Thank you for sing up to the Relice app! Please click the link below to activate" +
                " your account: " + "http://localhost:8080/sing-up/confirm?token=" + token);
        emailService.sendEmail(simpleMailMessage);
    }
}
