package com.frsummit.HRM.api.generic.model;

import java.util.List;

/**
 * 
 * @author hfoko
 *
 */
public class EndpointDoc {

    private String                 title;
    private String                 description;
    private List<EndpointDocParam> parameters;
    private EndpointDocRequest     request;
    private EndpointDocResponse    response;

    /**
     * 
     */
    public EndpointDoc() {
        super();
    }

    /**
     * 
     * @param title
     * @param description
     * @param parameters
     * @param request
     * @param response
     */
    public EndpointDoc(
            String title,
            String description,
            List<EndpointDocParam> parameters,
            EndpointDocRequest request,
            EndpointDocResponse response ) {
        super();
        this.title = title;
        this.description = description;
        this.parameters = parameters;
        this.setRequest( request );
        this.response = response;
    }

    /**
     * 
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
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

    /**
     * 
     * @return
     */
    public List<EndpointDocParam> getParameters() {
        return parameters;
    }

    /**
     * 
     * @param parameters
     */
    public void setParameters( List<EndpointDocParam> parameters ) {
        this.parameters = parameters;
    }

    /**
     * 
     * @return
     */
    public EndpointDocResponse getResponse() {
        return response;
    }

    /**
     * 
     * @param response
     */
    public void setResponse( EndpointDocResponse response ) {
        this.response = response;
    }

    /**
     * 
     * @return
     */
    public EndpointDocRequest getRequest() {
        return request;
    }

    /**
     * 
     * @param request
     */
    public void setRequest( EndpointDocRequest request ) {
        this.request = request;
    }

}
