package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Controller.ExceptionHandler.HandlerUtil;
import com.mrfisherman.relice.Dto.MinimalUserDto;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getUsersByGroup", params = "id")
    public Set<MinimalUserDto> getUsersByGroup(Authentication authentication, @RequestParam Long id) {
        return userService.getUsersByGroup(authentication, id);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User successfully added!");
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam String email, Authentication authentication) {
        userService.deleteUserByEmail(email, authentication);
        return ResponseEntity.ok("User successfully deleted!");
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public HashMap<String, String> handleIncorrectAuthority(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError("User have to be admin to see other users in group.", e);
    }

    @ExceptionHandler({SecurityException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public HashMap<String, String> handleIncorrectGroupId(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError("Group id is not the same as current user group id!", e);
    }

}
