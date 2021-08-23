package com.frsummit.HRM.api.generic.model;

import java.util.List;

/**
 * 
 * @author hfoko
 *
 */
public class EndpointParamValidator {

    private String       className;
    private List<String> constructorParamTypes;
    private List<String> constructorParams;

    /**
     * 
     */
    public EndpointParamValidator() {
    }

    /**
     * 
     * @param className
     * @param constructorParamTypes
     * @param constructorParams
     */
    public EndpointParamValidator(
            String className,
            List<String> constructorParamTypes,
            List<String> constructorParams ) {
        this.className = className;
        this.constructorParamTypes = constructorParamTypes;
        this.constructorParams = constructorParams;
    }

    /**
     * 
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     * 
     * @param className
     */
    public void setClassName( String className ) {
        this.className = className;
    }

    /**
     * 
     * @return
     */
    public List<String> getConstructorParamTypes() {
        return constructorParamTypes;
    }

    /**
     * 
     * @param constructorParamTypes
     */
    public void setConstructorParamTypes( List<String> constructorParamTypes ) {
        this.constructorParamTypes = constructorParamTypes;
    }

    /**
     * 
     * @return
     */
    public List<String> getConstructorParams() {
        return constructorParams;
    }

    /**
     * 
     * @param constructorParams
     */
    public void setConstructorParams( List<String> constructorParams ) {
        this.constructorParams = constructorParams;
    }

}
