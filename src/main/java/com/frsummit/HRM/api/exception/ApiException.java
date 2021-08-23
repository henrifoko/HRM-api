package com.frsummit.HRM.api.exception;

/**
 * 
 * @author hfoko
 *
 */
public class ApiException extends Exception implements CanGenerateApiError {

    private static final long serialVersionUID = -5590442769425406661L;

    protected ApiError        apiError;

    public ApiException( ApiError apiError ) {
        super();
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError( ApiError apiError ) {
        this.apiError = apiError;
    }

    @Override
    public ApiError generateApiError() {
        return apiError;
    }

}
