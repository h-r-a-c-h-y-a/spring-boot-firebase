package com.cerebrum.demo.bootfirebaseauth.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.cerebrum.demo.bootfirebaseauth.model.RequestUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ArangoRepository<RequestUser, String> {

    Optional<RequestUser> findByEmailAndPassword(String email, String password);
}
