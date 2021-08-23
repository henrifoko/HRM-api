package com.frsummit.HRM.api.security.model.request;

import java.util.Set;

public class SignupRequest {
    // TODO add a validator for username
    private String      username;
    // TODO add a validator for email
    private String      email;
    // TODO add a validator for password
    private String      password;
    // TODO add a validator here
    private Set<String> roles;

    public SignupRequest( String username, String email, String password, Set<String> roles ) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public SignupRequest() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles( Set<String> roles ) {
        this.roles = roles;
    }

}
