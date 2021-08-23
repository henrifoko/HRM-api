package com.frsummit.HRM.api.generic.model;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author hfoko
 *
 */
public enum EndpointHttpMethod {
    /**
     * 
     */
    GET( RequestMethod.GET ),

    /**
     * 
     */
    POST( RequestMethod.POST ),

    /**
     * 
     */
    PUT( RequestMethod.PUT ),

    /**
     * 
     */
    PATCH( RequestMethod.PATCH ),

    /**
     * 
     */
    DELETE( RequestMethod.DELETE );

    private RequestMethod method;

    private EndpointHttpMethod( RequestMethod method ) {
        this.method = method;
    }

    /**
     * 
     * @return
     */
    public RequestMethod getRequestMethod() {
        return method;
    }
}
