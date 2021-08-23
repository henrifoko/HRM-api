package com.frsummit.HRM.api.exception;

public class ApiSubErrorBadParamFormat extends ApiSubError {

    private String param;
    private String format;
    private String message;

    public ApiSubErrorBadParamFormat( String param, String format, String message ) {
        super();
        this.param = param;
        this.format = format;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public ApiSubErrorBadParamFormat() {
        super();
    }

    public String getParam() {
        return param;
    }

    public void setParam( String param ) {
        this.param = param;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat( String format ) {
        this.format = format;
    }

}
