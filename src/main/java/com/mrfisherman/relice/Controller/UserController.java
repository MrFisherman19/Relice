package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Dto.Wrapper.LoginWrapper;
import com.mrfisherman.relice.Dto.Wrapper.RegisterWrapper;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import com.mrfisherman.relice.Service.User.UserConfirmationTokenService;
import com.mrfisherman.relice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(value = "/sign-in", consumes = "application/json")
    public String signIn(@RequestBody LoginWrapper loginWrapper) {
        userService.signInUser(loginWrapper.getUsername(), loginWrapper.getPassword());
        return "blabla";
    }

    @PostMapping(value = "/sign-up", consumes = "application/json")
    public String signUp(@RequestBody RegisterWrapper registerWrapper) {
        User newUser = new User();
        newUser.setName(registerWrapper.getName());
        newUser.setEmail(registerWrapper.getEmail());
        newUser.setPassword(registerWrapper.getPassword());
        userService.signUpUser(newUser);
        return "redirect:/localhost:8080/signIn";
    }

    @GetMapping("/user")
    User getUser(@RequestParam("id") Long id) {
        return userService.findById(id).orElseThrow(NullPointerException::new);
    }

    @GetMapping("/userDetails")
    User getUserDetails(@RequestParam("id") Long id) {
        return userService.findById(id).orElseThrow(NullPointerException::new);
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {
        UserConfirmationToken optionalUserConfirmationToken = userConfirmationTokenService.findConfirmationTokenByToken(token);
        userService.confirmUser(optionalUserConfirmationToken);
        return "/sign-in";
    }

}
