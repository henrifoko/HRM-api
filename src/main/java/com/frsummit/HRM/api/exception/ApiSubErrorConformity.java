package com.frsummit.HRM.api.exception;

public class ApiSubErrorConformity extends ApiSubError {

    private String parameter;
    private String message;

    public ApiSubErrorConformity( String parameter, String message ) {
        super();
        this.parameter = parameter;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public String getParameter() {
        return parameter;
    }

    public void setName( String parameter ) {
        this.parameter = parameter;
    }

}
