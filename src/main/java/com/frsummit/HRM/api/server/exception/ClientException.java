package com.frsummit.HRM.api.server.exception;

import com.frsummit.HRM.api.exception.ApiError;
import com.frsummit.HRM.api.exception.ApiException;

/**
 * 
 */
public class ClientException extends ApiException {

    /**
     * 
     */
    private static final long serialVersionUID = -3164910120711617525L;

    /**
     * 
     */
    public ClientException( ApiError apiError ) {
        super( apiError );
    }

}
