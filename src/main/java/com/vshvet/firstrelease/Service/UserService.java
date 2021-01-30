package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.User;
import com.vshvet.firstrelease.Entity.VerificationToken;
import com.vshvet.firstrelease.Payload.Request.LoginRequest;
import com.vshvet.firstrelease.Payload.Request.SignupRequest;
import com.vshvet.firstrelease.Payload.Response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    JwtResponse authUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);

    User findByName(String name);

    boolean thisUserEnable(String name);

    void userVerification(User user);

    void createVerificationTokenForUser(final User user, final String token);

    VerificationToken getVerificationToken(String VerificationToken);


}
