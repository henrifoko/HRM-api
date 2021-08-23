package com.frsummit.HRM.api.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author hfoko
 *
 */
public class ApiParamFormatException extends ApiException {

    private static final long serialVersionUID = 2832988279669903728L;

    public ApiParamFormatException( List<ApiSubError> subErrors ) {
        super(
                new ApiError(
                        ApiErrorCode.REQUEST_ERROR,
                        HttpStatus.BAD_REQUEST,
                        "Bad request",
                        "Please refers to documentation of this endpoint",
                        subErrors ) );
    }

}
