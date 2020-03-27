package com.cerebrum.demo.bootfirebaseauth.controller;

import com.cerebrum.demo.bootfirebaseauth.model.RequestUser;
import com.cerebrum.demo.bootfirebaseauth.model.ResponseUser;
import com.cerebrum.demo.bootfirebaseauth.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<?> getByUID(@PathVariable String uid) {
        RequestUser user = this.userService.getByUID(uid).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit/{uid}")
    public ResponseEntity<?> update(@PathVariable String uid, @RequestBody RequestUser user) throws FirebaseAuthException {
        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(user.getId());
        request.setDisplayName(user.getName()).setEmail(user.getEmail()).setPhoneNumber(user.getPhones()[0]);
        FirebaseAuth.getInstance().updateUser(request);
        RequestUser updateUser = userService.update(uid, user).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(new ResponseUser(updateUser.getId(), updateUser.getEmail(), updateUser.getName(), updateUser.getPhones()), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        UserRecord userRecord = (UserRecord) request.getAttribute("user");
        Optional<RequestUser> optional = userService.getByUID(userRecord.getUid());
        RequestUser user =  optional.orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(new ResponseUser(user.getId(), user.getEmail(), user.getName(), user.getPhones()), HttpStatus.OK);
    }
}
