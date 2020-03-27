package com.cerebrum.demo.bootfirebaseauth.controller;

import com.cerebrum.demo.bootfirebaseauth.model.LoginForm;
import com.cerebrum.demo.bootfirebaseauth.model.RequestUser;
import com.cerebrum.demo.bootfirebaseauth.model.ResponseUser;
import com.cerebrum.demo.bootfirebaseauth.service.AuthService;
import com.cerebrum.demo.bootfirebaseauth.service.UserService;
import com.google.firebase.auth.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/auth")
@RestController
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RequestUser user) throws FirebaseAuthException {
        RequestUser newUser = userService.register(user)
                .orElseThrow(RuntimeException::new);
        return  ResponseEntity.ok(new ResponseUser(
                newUser.getId(), newUser.getEmail(), newUser.getName(), newUser.getPhones()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) throws FirebaseAuthException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        UserRecord userRecord = null;
        userRecord = auth.getUserByEmail(loginForm.getEmail());
        if (!userRecord.isEmailVerified()) {
            throw new FirebaseAuthException(String.valueOf(HttpStatus.UNAUTHORIZED), "Please verify your email and try again");
        }
        RequestUser user = userService.login(loginForm.getEmail(), DigestUtils.sha256Hex(loginForm.getPassword()))
                .orElseThrow(() -> new RuntimeException("Wrong password or email"));
        Map<String, Object> map = new HashMap<>();
        map.put("token", auth.createCustomToken(userRecord.getUid()));
        map.put("client", new ResponseUser(user.getId(), user.getEmail(), user.getName(), user.getPhones()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

