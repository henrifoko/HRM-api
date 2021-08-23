package com.frsummit.HRM.api.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author hfoko
 *
 */
public class ApiRequestConformityException extends ApiException {

    private static final long serialVersionUID = 3170710040984135522L;

    public ApiRequestConformityException( List<ApiSubError> subErrors ) {
        super(
                new ApiError(
                        ApiErrorCode.REQUEST_ERROR,
                        HttpStatus.BAD_REQUEST,
                        "Bad request",
                        "Please check the documention",
                        subErrors ) );
    }

}
