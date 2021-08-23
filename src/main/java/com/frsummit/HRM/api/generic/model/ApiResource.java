package com.frsummit.HRM.api.generic.model;

import java.util.List;

/**
 * 
 * @author hfoko
 *
 */
public class ApiResource {
    private String               name;
    private String               uri;
    private List<EndpointConfig> endpoints;

    /**
     * 
     */
    public ApiResource() {
        super();
    }

    /**
     * 
     * @param name
     * @param uri
     * @param endpoints
     */
    public ApiResource( String name, String uri, List<EndpointConfig> endpoints ) {
        this.name = name;
        this.uri = uri;
        this.setEndpoints( endpoints );
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * 
     * @return
     */
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     */
    public void setUri( String uri ) {
        this.uri = uri;
    }

    /**
     * 
     * @return
     */
    public List<EndpointConfig> getEndpoints() {
        return endpoints;
    }

    /**
     * 
     * @param endpoints
     */
    public void setEndpoints( List<EndpointConfig> endpoints ) {
        this.endpoints = endpoints;
    }

}
