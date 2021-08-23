package com.frsummit.HRM.api.generic.validator;

/**
 * 
 * @author hfoko
 *
 */
abstract public class AbstractValidator implements ApiValidator {

    protected String param;

    /**
     * 
     * @param param
     */
    AbstractValidator( String param ) {
        this.param = param;
    }

    /**
     * 
     * @return
     */
    public String getParam() {
        return param;
    }

    /**
     * 
     * @param param
     */
    public void setParam( String param ) {
        this.param = param;
    }

}
