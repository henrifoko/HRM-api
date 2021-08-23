package com.frsummit.HRM.api.exception;

/**
 * 
 * @author hfoko
 *
 */
public class ApiSortPostprocessingException extends ApiException {

    private static final long serialVersionUID = 8049630819337451988L;

    public ApiSortPostprocessingException( ApiError apiError ) {
        super( apiError );
    }

    @Override
    public ApiError generateApiError() {
        return apiError;
    }

}
