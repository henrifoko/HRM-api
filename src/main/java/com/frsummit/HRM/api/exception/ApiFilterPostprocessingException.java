package com.frsummit.HRM.api.exception;

/**
 * 
 * @author hfoko
 *
 */
public class ApiFilterPostprocessingException extends ApiException {

    private static final long serialVersionUID = 2579310300836435642L;

    /**
     *
     * @param apiError
     */
    public ApiFilterPostprocessingException(
            ApiError apiError ) {
        super( apiError );
    }

    @Override
    public ApiError generateApiError() {
        return apiError;
    }

}
