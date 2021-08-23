package com.frsummit.HRM.api.exception;

public class ApiSubErrorURISyntax extends ApiSubError {

    private String uri;
    private String debug;
    private String reason;

    public ApiSubErrorURISyntax( String uri, String debug, String reason ) {
        super();
        this.uri = uri;
        this.debug = debug;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason( String reason ) {
        this.reason = reason;
    }

    public String getUri() {
        return uri;
    }

    public void setUri( String uri ) {
        this.uri = uri;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug( String debug ) {
        this.debug = debug;
    }

}
