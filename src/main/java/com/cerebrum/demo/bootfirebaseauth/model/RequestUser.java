package com.cerebrum.demo.bootfirebaseauth.model;

import com.arangodb.springframework.annotation.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@Document("users")
public class RequestUser {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    private String password;
    private String[] phones;
}
