package com.frsummit.HRM.api.rmi.entity;

import java.io.Serializable;

public class Links implements Serializable {
    private int id;
    private String linkName;
    private String url;

    public Links() {
    }

    public Links(String linkName, String url) {
        this.linkName = linkName;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
