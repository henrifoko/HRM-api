package com.frsummit.HRM.api.exception;

/**
 * 
 * @author hfoko
 *
 */
public class ApiPaginationPostprocessingException extends ApiException {

    private static final long serialVersionUID = 2579310300836435642L;

    public ApiPaginationPostprocessingException( ApiError apiError ) {
        super( apiError );
    }

    @Override
    public ApiError generateApiError() {
        return apiError;
    }

}
