package com.example.models;


public class ReqUser {

    private String username;
    private String pass;

    public ReqUser() {
        super();
    }

    public ReqUser(String username, String pass) {
        super();
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }

}