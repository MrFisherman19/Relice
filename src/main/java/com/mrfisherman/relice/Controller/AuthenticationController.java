package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Configuration.Security.JwtTokenUtil;
import com.mrfisherman.relice.Controller.ExceptionHandler.HandlerUtil;
import com.mrfisherman.relice.Dto.Wrapper.LoginRequest;
import com.mrfisherman.relice.Dto.Wrapper.LoginResponse;
import com.mrfisherman.relice.Dto.Wrapper.RegisterRequest;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Exception.UserAlreadyExistException;
import com.mrfisherman.relice.Service.User.UserConfirmationTokenService;
import com.mrfisherman.relice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserConfirmationTokenService userConfirmationTokenService;
    private final JwtTokenUtil jwtTokenUtil;

    private final static String AFTER_TOKEN_CONFIRM_RELOCATION_URL = "http://localhost:8080/sign-in";

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

        userService.signUpUser(newUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/sign-in", consumes = "application/json")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/confirm")
    ResponseEntity<HttpHeaders> confirmMail(@RequestParam("token") String token) throws Exception {
        userService.confirmUser(userConfirmationTokenService.findConfirmationTokenByToken(token)
                .orElseThrow(() -> new Exception("Token is invalid yet.")));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", AFTER_TOKEN_CONFIRM_RELOCATION_URL);

        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }

    @ExceptionHandler({DisabledException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public HashMap<String, String> handleUnverifiedUser(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError("User is unverified yet or disabled.", e);
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public HashMap<String, String> handleIncorrectCredentials(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError("Incorrect username or password.", e);
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleUserAlreadyExist(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError("User with this email already exist", e);
    }

}
