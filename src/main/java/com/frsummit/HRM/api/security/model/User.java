package com.frsummit.HRM.api.security.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @author hfoko
 *
 */
@Entity
@Table( name = "users", uniqueConstraints = {
        @UniqueConstraint( columnNames = "username" ),
        @UniqueConstraint( columnNames = "email" ) } )
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long      id;

    @NotBlank
    @Size( max = 20 )
    private String    username;

    @NotBlank
    @Size( max = 50 )
    @Email
    private String    email;

    @NotBlank
    @Size( max = 120 )
    private String    password;

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable( name = "user_roles", joinColumns = @JoinColumn( name = "user_id" ), inverseJoinColumns = @JoinColumn( name = "role_id" ) )
    private Set<Role> roles = new HashSet<Role>();

    /**
     * 
     */
    public User() {
    }

    /**
     * 
     * @param username
     * @param email
     * @param password
     */
    public User( String username, String email, String password ) {
        this.username = username;
        this.email = email;
        this.password = password;
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
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     */
    public void setPassword( String password ) {
        this.password = password;
    }

    /**
     * 
     * @return
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * 
     * @param roles
     */
    public void setRoles( Set<Role> roles ) {
        this.roles = roles;
    }
}
