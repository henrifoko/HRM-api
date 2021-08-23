package com.frsummit.HRM.api.generic.model;

import java.util.List;

import com.frsummit.HRM.api.server.shared.InvokerPostprocessingStrategyEnum;
import com.frsummit.HRM.api.server.shared.InvokerPreprocessingStrategyEnum;

/**
 * 
 * @author hfoko
 *
 */
public class EndpointConfigInvocation {

    private String                            bean;
    private String                            method;
    private List<EndpointParam>               params;

    private InvokerPostprocessingStrategyEnum invokerPostprocessingStrategy;
    private InvokerPreprocessingStrategyEnum  invokerPreprocessingStrategy;

    /**
     * 
     */
    public EndpointConfigInvocation() {
    }

    /**
     * 
     * @param bean
     * @param method
     * @param params
     * @param invokerPostprocessingStrategy
     * @param invokerPreprocessingStrategy
     */
    public EndpointConfigInvocation(
            String bean,
            String method,
            List<EndpointParam> params,
            InvokerPostprocessingStrategyEnum invokerPostprocessingStrategy,
            InvokerPreprocessingStrategyEnum invokerPreprocessingStrategy ) {
        this.bean = bean;
        this.method = method;
        this.params = params;
        this.invokerPostprocessingStrategy = invokerPostprocessingStrategy;
        this.invokerPreprocessingStrategy = invokerPreprocessingStrategy;
    }

    /**
     * 
     * @return
     */
    public String getBean() {
        return bean;
    }

    /**
     * 
     * @param bean
     */
    public void setBean( String bean ) {
        this.bean = bean;
    }

    /**
     * 
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     * 
     * @param method
     */
    public void setMethod( String method ) {
        this.method = method;
    }

    /**
     * 
     * @return
     */
    public List<EndpointParam> getParams() {
        return params;
    }

    /**
     * 
     * @param params
     */
    public void setParams( List<EndpointParam> params ) {
        this.params = params;
    }

    /**
     * 
     * @return
     */
    public InvokerPostprocessingStrategyEnum getInvokerPostprocessingStrategy() {
        return invokerPostprocessingStrategy;
    }

    /**
     * 
     * @param invokerPostprocessingStrategy
     */
    public void setInvokerPostprocessingStrategy( InvokerPostprocessingStrategyEnum invokerPostprocessingStrategy ) {
        this.invokerPostprocessingStrategy = invokerPostprocessingStrategy;
    }

    /**
     * 
     * @return
     */
    public InvokerPreprocessingStrategyEnum getInvokerPreprocessingStrategy() {
        return invokerPreprocessingStrategy;
    }

    /**
     * 
     * @param invokerPreprocessingStrategy
     */
    public void setInvokerPreprocessingStrategy( InvokerPreprocessingStrategyEnum invokerPreprocessingStrategy ) {
        this.invokerPreprocessingStrategy = invokerPreprocessingStrategy;
    }

}
