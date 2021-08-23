package com.frsummit.HRM.api.generic.model;

import java.util.HashMap;

/**
 * 
 * @author hfoko
 *
 */
public class EndpointConfig {

    // private LoggingConfig loggingConfig;
    // private cachingConfig cachingConfig;

    // General properties
    protected String                       name;
    protected String                       version;

    // Identification properties
    protected String                       uriPattern;
    protected EndpointHttpMethod           method;

    // Security properties
    protected EndpointConfigSecurity       security;

    // Documentation properties
    protected HashMap<String, EndpointDoc> docs;

    // Invocation properties
    protected EndpointConfigInvocation     invocation;

    // Filters properties
    protected FilterPostprocessingEnum     postprocessing;
    protected FilterPreprocessingEnum      preprocessing;

    // Logging properties
    // Coming soon...

    // Caching properties
    // Coming soon...

    /**
     * 
     */
    public EndpointConfig() {
    }

    /**
     * 
     * @param name
     * @param version
     * @param uriPattern
     * @param method
     * @param security
     * @param docs
     * @param invocation
     * @param postprocessing
     * @param preprocessing
     */
    public EndpointConfig(
            String name,
            String version,
            String uriPattern,
            EndpointHttpMethod method,
            EndpointConfigSecurity security,
            HashMap<String, EndpointDoc> docs,
            EndpointConfigInvocation invocation,
            FilterPostprocessingEnum postprocessing,
            FilterPreprocessingEnum preprocessing ) {
        this.name = name;
        this.version = version;
        this.uriPattern = uriPattern;
        this.method = method;
        this.security = security;
        this.docs = docs;
        this.invocation = invocation;
        this.postprocessing = postprocessing;
        this.preprocessing = preprocessing;
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
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     */
    public void setVersion( String version ) {
        this.version = version;
    }

    /**
     * 
     * @return
     */
    public String getUriPattern() {
        return uriPattern;
    }

    /**
     * 
     * @param uriPattern
     */
    public void setUriPattern( String uriPattern ) {
        this.uriPattern = uriPattern;
    }

    /**
     * 
     * @return
     */
    public EndpointHttpMethod getMethod() {
        return method;
    }

    /**
     * 
     * @param method
     */
    public void setMethod( EndpointHttpMethod method ) {
        this.method = method;
    }

    /**
     * 
     * @return
     */
    public EndpointConfigSecurity getSecurity() {
        return security;
    }

    /**
     * 
     * @param security
     */
    public void setSecurity( EndpointConfigSecurity security ) {
        this.security = security;
    }

    /**
     * 
     * @return
     */
    public HashMap<String, EndpointDoc> getDocs() {
        return docs;
    }

    /**
     * 
     * @param docs
     */
    public void setDocs( HashMap<String, EndpointDoc> docs ) {
        this.docs = docs;
    }

    /**
     * 
     * @return
     */
    public EndpointConfigInvocation getInvocation() {
        return invocation;
    }

    /**
     * 
     * @param invocation
     */
    public void setInvocation( EndpointConfigInvocation invocation ) {
        this.invocation = invocation;
    }

    /**
     * 
     * @return
     */
    public FilterPostprocessingEnum getPostprocessing() {
        return postprocessing;
    }

    /**
     * 
     * @param postprocessing
     */
    public void setPostprocessing( FilterPostprocessingEnum postprocessing ) {
        this.postprocessing = postprocessing;
    }

    /**
     * 
     * @return
     */
    public FilterPreprocessingEnum getPreprocessing() {
        return preprocessing;
    }

    /**
     * 
     * @param preprocessing
     */
    public void setPreprocessing( FilterPreprocessingEnum preprocessing ) {
        this.preprocessing = preprocessing;
    }

}
