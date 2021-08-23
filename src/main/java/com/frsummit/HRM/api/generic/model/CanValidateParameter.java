package com.frsummit.HRM.api.generic.model;

import com.frsummit.HRM.api.exception.ApiServerException;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public interface CanValidateParameter {

    /**
     * 
     * @param val
     * @return
     * @throws ApiServerException
     * @throws ApiRequestValidationException
     */
    public boolean validate( Object val ) throws ApiServerException, ApiValidationException;
}
