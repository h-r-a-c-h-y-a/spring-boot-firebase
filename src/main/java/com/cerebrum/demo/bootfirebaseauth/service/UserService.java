package com.cerebrum.demo.bootfirebaseauth.service;

import com.cerebrum.demo.bootfirebaseauth.model.RequestUser;

import java.util.Optional;

public interface UserService {

    Optional<RequestUser> register(RequestUser user);

    Optional<RequestUser> login(String email, String password);

    Optional<RequestUser> getByUID(String uid);

    Optional<RequestUser> update(String uid, RequestUser user);
}
