package com.frsummit.HRM.api.security.model.response;

import java.util.Date;

/**
 * 
 * @author hfoko
 *
 */
public class ErrorMessage {
    private int    status;
    private Date   date;
    private String message;
    private String description;

    /**
     * 
     */
    public ErrorMessage() {
        super();
    }

    /**
     * 
     * @param status
     * @param date
     * @param message
     * @param description
     */
    public ErrorMessage( int status, Date date, String message, String description ) {
        super();
        this.status = status;
        this.date = date;
        this.message = message;
        this.description = description;
    }

    /**
     * 
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     */
    public void setStatus( int status ) {
        this.status = status;
    }

    /**
     * 
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date
     */
    public void setDate( Date date ) {
        this.date = date;
    }

    /**
     * 
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     */
    public void setMessage( String message ) {
        this.message = message;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription( String description ) {
        this.description = description;
    }

}
