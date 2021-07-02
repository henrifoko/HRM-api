package com.frsummit.HRM.api.rmi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Leaves implements Serializable {

    private int id;
    private String userId;
    private Date leaveApplyDate;
    private Date leaveApplyFrom;
    private Date leaveApplyTo;
    private int totalDayOfLeave;
    private String leaveReason;
    private String leaveType;
    private String leaveDescription;
    private String leaveStatus;
    private String applyWhom;
    private String modifyToWhom;
    private String leaveActionBy;
    private String cancellationLeaveStatus;
    private Set<User> userLeaves;

    public Leaves() {
    }

    public Leaves(String userId, Date leaveApplyFrom, Date leaveApplyTo, int totalDayOfLeave, String leaveDescription,
                  String leaveReason, String leaveType, String leaveStatus, String applyWhom, String modifyToWhom,
                  String leaveActionBy, String cancellationLeaveStatus) {
        this.userId = userId;
        this.leaveApplyFrom = leaveApplyFrom;
        this.leaveApplyTo = leaveApplyTo;
        this.totalDayOfLeave = totalDayOfLeave;
        this.leaveDescription = leaveDescription;
        this.leaveReason = leaveReason;
        this.leaveType = leaveType;
        this.leaveStatus = leaveStatus;
        this.applyWhom = applyWhom;
        this.modifyToWhom = modifyToWhom;
        this.leaveActionBy = leaveActionBy;
        this.cancellationLeaveStatus = cancellationLeaveStatus;
    }

    public Leaves(String cancellationLeaveStatus) {
        this.cancellationLeaveStatus = cancellationLeaveStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLeaveApplyDate() {
        return leaveApplyDate;
    }

    public void setLeaveApplyDate(Date leaveApplyDate) {
        this.leaveApplyDate = leaveApplyDate;
    }

    public Date getLeaveApplyFrom() {
        return leaveApplyFrom;
    }

    public void setLeaveApplyFrom(Date leaveApplyFrom) {
        this.leaveApplyFrom = leaveApplyFrom;
    }

    public Date getLeaveApplyTo() {
        return leaveApplyTo;
    }

    public void setLeaveApplyTo(Date leaveApplyTo) {
        this.leaveApplyTo = leaveApplyTo;
    }

    public int getTotalDayOfLeave() {
        return totalDayOfLeave;
    }

    public void setTotalDayOfLeave(int totalDayOfLeave) {
        this.totalDayOfLeave = totalDayOfLeave;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveDescription() {
        return leaveDescription;
    }

    public void setLeaveDescription(String leaveDescription) {
        this.leaveDescription = leaveDescription;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getApplyWhom() {
        return applyWhom;
    }

    public void setApplyWhom(String applyWhom) {
        this.applyWhom = applyWhom;
    }

    public String getModifyToWhom() {
        return modifyToWhom;
    }

    public void setModifyToWhom(String modifyToWhom) {
        this.modifyToWhom = modifyToWhom;
    }

    public String getLeaveActionBy() {
        return leaveActionBy;
    }

    public void setLeaveActionBy(String leaveActionBy) {
        this.leaveActionBy = leaveActionBy;
    }

    public String getCancellationLeaveStatus() {
        return cancellationLeaveStatus;
    }

    public void setCancellationLeaveStatus(String cancellationLeaveStatus) {
        this.cancellationLeaveStatus = cancellationLeaveStatus;
    }

    public Set<User> getUserLeaves() {
        return userLeaves;
    }

    public void setUserLeaves(Set<User> userLeaves) {
        this.userLeaves = userLeaves;
    }
}
