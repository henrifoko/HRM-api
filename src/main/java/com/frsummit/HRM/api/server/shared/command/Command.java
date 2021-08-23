package com.frsummit.HRM.api.server.shared.command;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.frsummit.HRM.api.server.shared.InvokerPostprocessingStrategyEnum;
import com.frsummit.HRM.api.server.shared.InvokerPreprocessingStrategyEnum;

public class Command implements Serializable {

    private static final long                 serialVersionUID = 6075035004943250991L;

    private String                            beanClassName;
    private String                            methodName;
    private String[]                          paramTypes;
    private Object[]                          params;

    // ========================================
    private InvokerPreprocessingStrategyEnum  preprocessingStrategy;
    private InvokerPostprocessingStrategyEnum postprocessingStrategy;

    /**
     * This reflective method is the entry door of the application. All customer
     * calls are performed in a reflective way through this method. All those
     * parameters are packed into the command object.
     * 
     * @author hfoko
     * @param serviceClassName{String}
     *            - Class name of the bean at the server side
     * @param remoteMethodName{String}
     *            - The name of the remote method
     * @param paramsType{String[]}
     *            - Array of parameter types of the called method
     * @param params{Object[]}
     *            - Array of parameters of the called method
     * @return This is the object returned by the called method in the case it
     *         returns an object
     * @throws RemoteException
     */
    public Command(
            String beanClassName,
            String methodName,
            String[] paramTypes,
            Object[] params,
            InvokerPreprocessingStrategyEnum preprocessingStrategy,
            InvokerPostprocessingStrategyEnum postprocessingStrategy ) {
        this.beanClassName = beanClassName;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;

        // ========================================
        this.preprocessingStrategy = preprocessingStrategy;
        this.postprocessingStrategy = postprocessingStrategy;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName( String beanClassName ) {
        this.beanClassName = beanClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName( String methodName ) {
        this.methodName = methodName;
    }

    public String[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes( String[] paramTypes ) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams( Object[] params ) {
        this.params = params;
    }

    // ========================================
    public InvokerPreprocessingStrategyEnum getPreprocessingStrategy() {
        return preprocessingStrategy;
    }

    public void setPreprocessingStrategy( InvokerPreprocessingStrategyEnum preprocessingStrategy ) {
        this.preprocessingStrategy = preprocessingStrategy;
    }

    public InvokerPostprocessingStrategyEnum getPostprocessingStrategy() {
        return postprocessingStrategy;
    }

    public void setPostprocessingStrategy( InvokerPostprocessingStrategyEnum postprocessingStrategy ) {
        this.postprocessingStrategy = postprocessingStrategy;
    }

}
