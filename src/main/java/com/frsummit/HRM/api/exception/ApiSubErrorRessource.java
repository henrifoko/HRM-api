package com.frsummit.HRM.api.exception;

public class ApiSubErrorRessource extends ApiSubError {

    private String url;
    private String method;

    public ApiSubErrorRessource() {
        super();
    }

    public ApiSubErrorRessource( String url, String method ) {
        super();
        this.url = url;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod( String method ) {
        this.method = method;
    }

}
