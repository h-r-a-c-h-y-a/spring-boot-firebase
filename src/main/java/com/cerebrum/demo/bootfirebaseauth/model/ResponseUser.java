package com.cerebrum.demo.bootfirebaseauth.model;

public class ResponseUser {

    private String id;


    private String email;

    private String name;

    private String[] phones;

    public ResponseUser(String id, String email, String name, String[] phones) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phones = phones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }
}
