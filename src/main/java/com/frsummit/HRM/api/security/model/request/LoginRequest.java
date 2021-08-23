package com.frsummit.HRM.api.security.model.request;

public class LoginRequest {
    // TODO add a validator for username
    private String username;
    // TODO add a validator for password
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
