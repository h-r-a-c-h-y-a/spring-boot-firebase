package com.cerebrum.demo.bootfirebaseauth.service.impl;

import com.cerebrum.demo.bootfirebaseauth.model.RequestUser;
import com.cerebrum.demo.bootfirebaseauth.repository.UserRepository;
import com.cerebrum.demo.bootfirebaseauth.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public Optional<RequestUser> register(RequestUser user) {
        if (user.getPassword() != null) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        }
        return Optional.of(userRepo.save(user));
    }

    @Override
    public Optional<RequestUser> login(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<RequestUser> getByUID(String uid) {
        return userRepo.findById(uid);
    }

    @Override
    public Optional<RequestUser> update(String uid, RequestUser user) {
        user.setId(uid);
        return Optional.of(userRepo.save(user));
    }
}
