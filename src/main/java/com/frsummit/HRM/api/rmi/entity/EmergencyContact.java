package com.frsummit.HRM.api.rmi.entity;

import java.io.Serializable;

public class EmergencyContact implements Serializable {

    private int id;
    private int leaveId;
    private String name;
    
    private String Address;

    private String phone;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "emergency_contact_user", joinColumns = @JoinColumn(name = "emergency_contact_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> userEmergencyContact;

    public EmergencyContact() {
    }

    public EmergencyContact(int leaveId, String name, String address, String phone) {
        this.leaveId = leaveId;
        this.name = name;
        Address = address;
        this.phone = phone;
    }

    public int getUserId() {
        return leaveId;
    }

    public void setUserId(int userId) {
        this.leaveId = leaveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
