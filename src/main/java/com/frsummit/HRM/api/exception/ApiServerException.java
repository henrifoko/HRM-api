package com.frsummit.HRM.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author hfoko
 * 
 */
public class ApiServerException extends ApiException {

    private static final long serialVersionUID = 4890989849017682399L;

    public ApiServerException() {
        super(
                new ApiError(
                        ApiErrorCode.SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error on the server",
                        "Please contact an administrator",
                        new ArrayList<>() ) );
    }

    public ApiServerException( List<ApiSubError> subErrors ) {
        super(
                new ApiError(
                        ApiErrorCode.SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error on the server",
                        "Please contact an administrator",
                        subErrors ) );
    }

}
