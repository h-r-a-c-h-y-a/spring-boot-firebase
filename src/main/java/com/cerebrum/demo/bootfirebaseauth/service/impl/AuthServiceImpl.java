package com.cerebrum.demo.bootfirebaseauth.service.impl;

import com.cerebrum.demo.bootfirebaseauth.model.RequestUser;
import com.cerebrum.demo.bootfirebaseauth.service.AuthService;
import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public void createUser(RequestUser user) throws FirebaseAuthException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setDisplayName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(DigestUtils.sha256Hex(user.getPassword()))
                .setEmailVerified(false)
                .setPhoneNumber(user.getPhones()[0])
                .setDisabled(false);
        ApiFuture<UserRecord> userRecord = auth.createUserAsync(request);
    }

    public UserRecord validateToken(String token) throws FirebaseAuthException {
        FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token);
        return FirebaseAuth.getInstance().getUser(firebaseToken.getUid());
    }
}
