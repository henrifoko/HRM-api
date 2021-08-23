package com.frsummit.HRM.api.security.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * @author hfoko
 *
 */
@Entity( name = "refreshtoken" )
public class RefreshToken {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long    id;

    @OneToOne
    @JoinColumn( name = "user_id", referencedColumnName = "id" )
    private User    user;

    @Column( nullable = false, unique = true )
    private String  token;

    @Column( nullable = false )
    private Instant expiryDate;

    /**
     * 
     */
    public RefreshToken() {
        super();
    }

    /**
     * 
     * @param id
     * @param user
     * @param token
     * @param expiryDate
     */
    public RefreshToken( Long id, User user, String token, Instant expiryDate ) {
        super();
        this.id = id;
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
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
    public User getUser() {
        return user;
    }

    /**
     * 
     * @param user
     */
    public void setUser( User user ) {
        this.user = user;
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
    public Instant getExpiryDate() {
        return expiryDate;
    }

    /**
     * 
     * @param expiryDate
     */
    public void setExpiryDate( Instant expiryDate ) {
        this.expiryDate = expiryDate;
    }

}
