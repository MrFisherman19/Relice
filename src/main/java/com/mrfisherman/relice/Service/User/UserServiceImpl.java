package com.mrfisherman.relice.Service.User;


import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import com.mrfisherman.relice.Repository.UserRepository;
import com.mrfisherman.relice.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.BadCredentialsException;
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
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
            return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));
    }

    @Override
    public void signUpUser(User user) throws BadCredentialsException {
        findByEmail(user.getEmail()).ifPresentOrElse(u ->
        { throw new BadCredentialsException("User with email: " + u.getEmail() + " already exist."); },
                () -> {
                    final String encryptedPassword = passwordEncoder.encode(user.getPassword());
                    user.setPassword(encryptedPassword);

                    final User createdUser = userRepository.save(user);
                    final UserConfirmationToken confirmationToken = new UserConfirmationToken(createdUser);

                    userConfirmationTokenService.saveConfirmationToken(confirmationToken);
                    sendConfirmationEmail(user.getEmail(), confirmationToken.getConfirmationToken());
        });
    }

    @Override
    public void confirmUser(UserConfirmationToken userConfirmationToken) {
        final User user = userConfirmationToken.getUser();

        user.setEnabled(true);

        userRepository.save(user);

        userConfirmationTokenService.deleteConfirmationToken(userConfirmationToken.getId());
    }

    @Override
    public void sendConfirmationEmail(String userEmail, String token) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userEmail);
        simpleMailMessage.setSubject("Welcome to Relice! Please confirm your email address!");
        simpleMailMessage.setFrom("<MAIL>");
        simpleMailMessage.setText("Thank you for sing up to the Relice app! \n\nPlease click the link below to activate" +
                " your account: \n" + "http://localhost:8081/confirm?token=" + token + "\n\nBest reards, \nRelice team");
        emailService.sendEmail(simpleMailMessage);
    }
}
