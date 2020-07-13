package com.vshvet.firstrelease.Controller;


import com.vshvet.firstrelease.payload.Request.LoginRequest;
import com.vshvet.firstrelease.payload.Request.SignupRequest;
import com.vshvet.firstrelease.security.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {


    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(userAuthService.authUser(loginRequest));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        return userAuthService.registerUser(signUpRequest);
    }
}
