package com.frsummit.HRM.api.generic.model;

/**
 * 
 * @author hfoko
 *
 */
public class EndpointDocParam {
    private boolean required;
    private String  description;

    /**
     * 
     */
    public EndpointDocParam() {
        super();
    }

    /**
     * 
     * @param required
     * @param description
     */
    public EndpointDocParam( boolean required, String description ) {
        this.required = required;
        this.description = description;
    }

    /**
     * 
     * @return
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * 
     * @param required
     */
    public void setRequired( boolean required ) {
        this.required = required;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription( String description ) {
        this.description = description;
    }

}
