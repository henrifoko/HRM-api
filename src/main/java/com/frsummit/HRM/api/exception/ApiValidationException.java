package com.frsummit.HRM.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiValidationException extends ApiException {

    private static final long serialVersionUID = 4573561421117914147L;

    public ApiValidationException( List<ApiSubError> subErrors ) {
        super( new ApiError(
                ApiErrorCode.REQUEST_ERROR,
                HttpStatus.BAD_REQUEST,
                "Validation error",
                "Please refer to the documentation to see the endpoint specifications",
                subErrors ) );
    }

    public ApiValidationException() {
        super( new ApiError(
                ApiErrorCode.REQUEST_ERROR,
                HttpStatus.BAD_REQUEST,
                "Validation error",
                "Please refer to the documentation to see the endpoint specifications",
                new ArrayList<>() ) );
    }

}
