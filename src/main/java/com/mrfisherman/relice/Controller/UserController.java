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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userDetails")
    User getUserDetailsByEmail(@RequestParam("email") String email) {
        return userService.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Cannot find user with email: " + email));
    }

}
