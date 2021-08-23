package com.frsummit.HRM.api.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author hfoko
 *
 */
public class ApiResourceNotFoundException extends ApiException {

    private static final long serialVersionUID = 8867299127493676104L;

    public ApiResourceNotFoundException( List<ApiSubError> subErrors ) {
        super( new ApiError(
                ApiErrorCode.REQUEST_ERROR,
                HttpStatus.NOT_FOUND,
                "Ressource not found",
                "Please refers to the documentation to see the endpoints exposed by this API",
                subErrors ) );
    }

}
