package com.frsummit.HRM.api.security.model.response;

/**
 * 
 * @author hfoko
 *
 */
public class MessageResponse {

    private String message;

    /**
     * 
     * @param message
     */
    public MessageResponse( String message ) {
        super();
        this.message = message;
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

}
