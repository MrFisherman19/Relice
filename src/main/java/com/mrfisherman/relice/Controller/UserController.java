package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Exception.UserNotFoundException;
import com.mrfisherman.relice.Service.User.UserConfirmationTokenService;
import com.mrfisherman.relice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    private final UserConfirmationTokenService userConfirmationTokenService;

    @Autowired
    public UserController(UserService userService, UserConfirmationTokenService userConfirmationTokenService) {
        this.userService = userService;
        this.userConfirmationTokenService = userConfirmationTokenService;
    }

    @GetMapping("/userDetails")
    User getUserDetailsByEmail(@RequestParam("email") String email) {
        return userService.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Cannot find user with email: " + email));
    }

}
