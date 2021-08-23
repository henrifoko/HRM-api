package com.frsummit.HRM.api.server.exception;

/**
 * 
 */
public class ApiExceptionAbstract extends Exception {

    private static final long serialVersionUID = -3788733274619676100L;
    private int               code;
    private String            message;

    /**
     * 
     */
    public ApiExceptionAbstract( int code, String message ) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * 
     */
    public void setCode( int code ) {
        this.code = code;
    }

    /**
     * 
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     */
    public void setMessage( String message ) {
        this.message = message;
    }

}
