package com.frsummit.HRM.api.generic.model;

import com.frsummit.HRM.api.exception.ApiParamFormatException;
import com.frsummit.HRM.api.exception.ApiServerException;

/**
 * 
 * @author hfoko
 *
 */
public interface CanParseParameter {

    /**
     * 
     * @param val
     * @return
     * @throws ApiParamFormatException
     * @throws ApiServerException
     */
    public Object parse( Object val ) throws ApiParamFormatException, ApiServerException;
}
