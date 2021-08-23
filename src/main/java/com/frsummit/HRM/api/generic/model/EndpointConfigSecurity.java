package com.frsummit.HRM.api.generic.model;

import java.util.List;

import com.frsummit.HRM.api.security.model.Role;

/**
 * 
 * @author hfoko
 *
 */
public class EndpointConfigSecurity {

    private boolean    authenticated;
    private List<Role> roles;

    /**
     * 
     */
    public EndpointConfigSecurity() {
        super();
    }

    /**
     * 
     * @param authenticated
     * @param roles
     */
    public EndpointConfigSecurity( boolean authenticated, List<Role> roles ) {
        super();
        this.authenticated = authenticated;
        this.roles = roles;
    }

    /**
     * 
     * @return
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * 
     * @param authenticated
     */
    public void setAuthenticated( boolean authenticated ) {
        this.authenticated = authenticated;
    }

    /**
     * 
     * @return
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * 
     * @param roles
     */
    public void setRoles( List<Role> roles ) {
        this.roles = roles;
    }

}
