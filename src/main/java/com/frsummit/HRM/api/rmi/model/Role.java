package com.frsummit.HRM.api.rmi.model;

import java.io.Serializable;

public class Role implements Serializable {

    private int id;
    private String role;
    private String roleChain;

    public Role() {
    }

    public Role(int id, String role, String roleChain) {
        this.id = id;
        this.role = role;
        this.roleChain = roleChain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleChain() {
        return roleChain;
    }

    public void setRoleChain(String roleChain) {
        this.roleChain = roleChain;
    }
}
