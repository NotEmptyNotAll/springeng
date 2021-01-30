package com.vshvet.firstrelease.Controller;


import com.vshvet.firstrelease.Entity.ERole;
import com.vshvet.firstrelease.Entity.User;
import com.vshvet.firstrelease.Entity.VerificationToken;
import com.vshvet.firstrelease.Payload.Request.LoginRequest;
import com.vshvet.firstrelease.Payload.Request.RegistrationRequest;
import com.vshvet.firstrelease.Payload.Request.SignupRequest;
import com.vshvet.firstrelease.Payload.Response.MessageResponse;
import com.vshvet.firstrelease.Registration.OnRegistrationCompleteEvent;
import com.vshvet.firstrelease.Service.Impl.UserAuthService;
import com.vshvet.firstrelease.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;

@RestController
public class AuthController {

    @Autowired
    private MessageSource messages;

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Value("${main.host}")
    private String host;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUserAccount(
            @RequestBody RegistrationRequest registrationRequest,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("пользователь с такими данными уже есть"));
        }

        User registered = new User(registrationRequest);
        userService.registerUser(new SignupRequest(registered));
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("пользователь с такими данными уже есть"));
        }
        return ResponseEntity.ok()
                .body(new MessageResponse("Ваш обліковий запис була підтверджена. Можете перейти на сторінку входу, і авторизуватися."));
    }


    @RequestMapping(value = "/regitrationConfirm/{token}", method = RequestMethod.GET)
    public RedirectView confirmRegistration
            (WebRequest request, Model model, @PathVariable String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return new RedirectView("http://" + host + ":5000/errorEmail");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return new RedirectView("http://" + host + ":5000/errorEmail");

        }

        userService.userVerification(user);
        return new RedirectView("http://" + host + ":5000/confirmEmail");
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        if (userService.thisUserEnable(loginRequest.getUsername())) {
            return ResponseEntity.ok(userService.authUser(loginRequest));
        } else {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Not enabled user"));
        }
    }


    @PostMapping("/signupsssz")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        return userService.registerUser(signUpRequest);
    }


}
