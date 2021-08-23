package com.frsummit.HRM.api.security.model.response;

import java.util.List;

import com.frsummit.HRM.api.security.model.RefreshToken;

/**
 * 
 * @author hfoko
 *
 */
public class JwtResponse {

    private String       token;
    private Long         id;
    private final String type = "Bearer";
    private RefreshToken refreshToken;
    private String       username;
    private String       email;
    private List<String> roles;

    /**
     * 
     * @param token
     * @param id
     * @param refreshToken
     * @param username
     * @param email
     * @param roles
     */
    public JwtResponse( String token, Long id, RefreshToken refreshToken, String username, String email,
            List<String> roles ) {
        super();
        this.token = token;
        this.id = id;
        this.refreshToken = refreshToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    /**
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @return
     */
    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    /**
     * 
     * @param refreshToken
     */
    public void setRefreshToken( RefreshToken refreshToken ) {
        this.refreshToken = refreshToken;
    }

    /**
     * 
     */
    public JwtResponse() {
        super();
    }

    /**
     * 
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     */
    public void setToken( String token ) {
        this.token = token;
    }

    /**
     * 
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     */
    public void setUsername( String username ) {
        this.username = username;
    }

    /**
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail( String email ) {
        this.email = email;
    }

    /**
     * 
     * @return
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * 
     * @param roles
     */
    public void setRoles( List<String> roles ) {
        this.roles = roles;
    }

}
