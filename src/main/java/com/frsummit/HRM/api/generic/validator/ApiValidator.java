package com.frsummit.HRM.api.generic.validator;

import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public interface ApiValidator {

    /**
     * 
     * @param val
     * @return
     * @throws ApiValidationException
     */
    public boolean validate( Object val ) throws ApiValidationException;
}
