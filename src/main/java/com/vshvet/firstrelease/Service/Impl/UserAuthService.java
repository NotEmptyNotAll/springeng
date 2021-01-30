package com.vshvet.firstrelease.Service.Impl;


import com.vshvet.firstrelease.DAO.RoleDao;
import com.vshvet.firstrelease.DAO.UserDao;
import com.vshvet.firstrelease.DAO.VerificationTokenDao;
import com.vshvet.firstrelease.Entity.ERole;
import com.vshvet.firstrelease.Entity.Role;
import com.vshvet.firstrelease.Entity.User;
import com.vshvet.firstrelease.Entity.VerificationToken;
import com.vshvet.firstrelease.Payload.Request.LoginRequest;
import com.vshvet.firstrelease.Payload.Request.SignupRequest;
import com.vshvet.firstrelease.Payload.Response.JwtResponse;
import com.vshvet.firstrelease.Payload.Response.MessageResponse;
import com.vshvet.firstrelease.Security.jwt.JwtUtils;
import com.vshvet.firstrelease.Security.services.UserDetailsImpl;
import com.vshvet.firstrelease.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private VerificationTokenDao verificationTokenDao;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Transactional(readOnly=true)
    public boolean thisUserEnable(String name) {
        return userDao.findByUsername(name).orElse(new User()).isEnabled();
    }

    @Override
    public JwtResponse authUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    @Transactional
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userDao.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userDao.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        System.out.println(encoder.encode("0508090947"));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleDao.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleDao.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleDao.findByName(ERole.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleDao.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        try {
            userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public User findByName(String name) {
        return userDao.findByUsername(name).get();
    }

    @Override
    public void userVerification(User user) {
        user.setEnabled(true);
        userDao.update(user);
    }

    @Override
    @Transactional
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        try {
            verificationTokenDao.save(myToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional
    public VerificationToken getVerificationToken(final String VerificationToken) {
        return verificationTokenDao.findByToken(VerificationToken);
    }

}
