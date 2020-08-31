package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import com.mrfisherman.relice.Service.User.UserConfirmationTokenService;
import com.mrfisherman.relice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;
    private final UserConfirmationTokenService userConfirmationTokenService;

    @Autowired
    public UserController(UserService userService, UserConfirmationTokenService userConfirmationTokenService) {
        this.userService = userService;
        this.userConfirmationTokenService = userConfirmationTokenService;
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(User user) {
        userService.singUpUser(user);
        return "redirect:/sign-in";
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") String email) {
        return userService.findByEmail(email).get();
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {
        Optional<UserConfirmationToken> optionalUserConfirmationToken = userConfirmationTokenService.findConfirmationTokenByToken(token);
        optionalUserConfirmationToken.ifPresent(userService::confirmUser);
        return "/sign-in";
    }

}
