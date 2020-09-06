package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Configuration.Security.JwtTokenUtil;
import com.mrfisherman.relice.Dto.Wrapper.LoginRequest;
import com.mrfisherman.relice.Dto.Wrapper.LoginResponse;
import com.mrfisherman.relice.Dto.Wrapper.RegisterRequest;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserConfirmationToken;
import com.mrfisherman.relice.Exception.RegistrationFailedException;
import com.mrfisherman.relice.Service.User.UserConfirmationTokenService;
import com.mrfisherman.relice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserConfirmationTokenService userConfirmationTokenService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationController(UserService userService, UserConfirmationTokenService userConfirmationTokenService,
                                    AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.userConfirmationTokenService = userConfirmationTokenService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/sign-up", consumes = "application/json")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest) {

        User newUser = new User();
        newUser.setName(registerRequest.getName());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(registerRequest.getPassword());

        try {
            userService.signUpUser(newUser);
        } catch (RegistrationFailedException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/sign-in", consumes = "application/json")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {
        UserConfirmationToken optionalUserConfirmationToken = userConfirmationTokenService.findConfirmationTokenByToken(token);
        userService.confirmUser(optionalUserConfirmationToken);
        return "/sign-in";
    }

}
