package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Dto.MinimalUserDto;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import com.mrfisherman.relice.Entity.User.UserGroup;
import com.mrfisherman.relice.Entity.User.UserRole;
import com.mrfisherman.relice.Exception.UserAlreadyExistException;
import com.mrfisherman.relice.Repository.UserRepository;
import com.mrfisherman.relice.Service.Email.EmailService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserConfirmationTokenService userConfirmationTokenService;
    private final EmailService emailService;
    private final ModelMapper modelMapper;
    private final UserGroupService userGroupService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConfirmationTokenService userConfirmationTokenService,
                           EmailService emailService, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper, UserGroupService userGroupService) {
        this.userRepository = userRepository;
        this.userConfirmationTokenService = userConfirmationTokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userGroupService = userGroupService;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        return findByEmail(email).orElseThrow(() -> new BadCredentialsException("Incorrect username or password."));
    }

    public MinimalUserDto loadUserWithoutPasswordByUsername(String email) {
        return modelMapper.map(loadUserByUsername(email), MinimalUserDto.class);
    }

    @Override
    public Set<MinimalUserDto> getUsersByGroup(Authentication authentication, Long groupId) {

        final User currentUser = (User) loadUserByUsername(authentication.getName());

        if (currentUser.getUserRole().equals(UserRole.ROLE_ADMIN)) {
            if (isUserInGroup(currentUser, groupId)) {
                return modelMapper.map(userRepository.findByGroupId(groupId), new TypeToken<Set<MinimalUserDto>>() {}.getType());
            } else {
                throw new SecurityException("Group id is not the same as current user group id!");
            }
        } else {
            throw new BadCredentialsException("User have to be admin to see other users in group.");
        }
    }

    private boolean isUserInGroup(User user, Long groupId) {
        return user.getGroup().getId().equals(groupId);
    }

    @Override
    public void addUser(User user) {
        findByEmail(user.getEmail()).ifPresentOrElse(this::throwUserAlreadyExistException, () ->  {
            setUserPassword(user);
            user.setGroup(userGroupService.findById(user.getGroup().getId()).orElseThrow(
                    () -> new EntityNotFoundException("No group with given id")));
            userRepository.save(user);
        });
    }

    @Override
    public void deleteUserByEmail(String email, Authentication authentication) {
        final User currentUser = (User) loadUserByUsername(authentication.getName());

        final User userToDelete = findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User with email " + email + " does not exist!"));

        if (isUserInGroup(userToDelete, currentUser.getGroup().getId())) {
            userRepository.delete(userToDelete);
        } else {
            throw new SecurityException("Cannot delete user from a different group!");
        }
    }

    @Override
    public void deleteCurrentUser(Authentication authentication) {
        userRepository.delete(findByEmail(authentication.getName()).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void signUpUser(User user) {
        findByEmail(user.getEmail()).ifPresentOrElse(this::throwUserAlreadyExistException, () -> signUp(user));
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

    private void throwUserAlreadyExistException(User u) {
        throw new UserAlreadyExistException(MessageFormat.format("User with email: {0} already exist.", u.getEmail()));
    }

    private void signUp(User user) {
        setUserPassword(user);

        user.setGroup(createNewGroup());

        final User createdUser = userRepository.save(user);

        final UserConfirmationToken confirmationToken = getUserConfirmationTokenForUser(createdUser);

        sendConfirmationEmail(user.getEmail(), confirmationToken.getConfirmationToken());
    }

    private UserGroup createNewGroup() {
        final UserGroup userGroup = new UserGroup();
        userGroupService.save(userGroup);
        return userGroup;
    }

    private UserConfirmationToken getUserConfirmationTokenForUser(User createdUser) {
        final UserConfirmationToken confirmationToken = new UserConfirmationToken(createdUser);
        userConfirmationTokenService.saveConfirmationToken(confirmationToken);
        return confirmationToken;
    }

    private void setUserPassword(User user) {
        final String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
    }
}
