package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Dto.MinimalUserDto;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getCurrentUserDetails")
    public MinimalUserDto getCurrentUserDetails(Authentication authentication) {
        return userService.loadUserWithoutPasswordByUsername(authentication.getName());
    }

    @DeleteMapping("/deleteCurrentUser")
    public ResponseEntity<?> deleteCurrentUser(Authentication authentication) {
        userService.deleteCurrentUser(authentication);
        return ResponseEntity.ok("User successfully deleted!");
    }

}


