package com.cerebrum.demo.bootfirebaseauth.service;

import com.cerebrum.demo.bootfirebaseauth.model.RequestUser;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

public interface AuthService {

    void createUser(RequestUser user) throws FirebaseAuthException;

    UserRecord validateToken(String token) throws FirebaseAuthException;
}
