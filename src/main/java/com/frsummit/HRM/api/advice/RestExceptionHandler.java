package com.frsummit.HRM.api.advice;

import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.frsummit.HRM.api.exception.ApiError;
import com.frsummit.HRM.api.exception.ApiErrorCode;
import com.frsummit.HRM.api.exception.ApiException;
import com.frsummit.HRM.api.exception.ApiNullParamTypeException;
import com.frsummit.HRM.api.exception.ApiParamFormatException;
import com.frsummit.HRM.api.exception.ApiRequestConformityException;
import com.frsummit.HRM.api.exception.ApiResourceNotFoundException;
import com.frsummit.HRM.api.exception.ApiServerException;
import com.frsummit.HRM.api.exception.ApiSubErrorURISyntax;
import com.frsummit.HRM.api.exception.ApiValidationException;
import com.frsummit.HRM.api.exception.CanGenerateApiError;
import com.frsummit.HRM.api.server.exception.ClientException;

@Order( Ordered.HIGHEST_PRECEDENCE )
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler( value = {
            ApiResourceNotFoundException.class,
            ApiRequestConformityException.class,
            ApiParamFormatException.class,
            ApiServerException.class,
            ApiValidationException.class
    } )
    protected ResponseEntity<ApiError> handleApiStandardException( Exception ex ) {
        return buildResponseEntity( ( (CanGenerateApiError) ex ).generateApiError() );
    }

    @ExceptionHandler( RemoteException.class )
    protected ResponseEntity<ApiError> handleApiRemoteException( RemoteException ex ) {
        Throwable cause = ex.getCause();
        if ( cause instanceof ClientException ) {
            return buildResponseEntity( ( (CanGenerateApiError) ex.getCause() ).generateApiError() );
        } else {
            return buildResponseEntity( ( new ApiServerException() ).generateApiError() );
        }
    }

    @ExceptionHandler( ApiNullParamTypeException.class )
    protected ResponseEntity<ApiError> handleNullParamTypeException( ApiNullParamTypeException ex ) {
        return handleApiStandardException( new ApiServerException() );
    }

    @ExceptionHandler( URISyntaxException.class )
    protected ResponseEntity<ApiError> handleURISyntaxException( URISyntaxException ex ) {
        return buildResponseEntity( ( new ApiException(
                new ApiError(
                        ApiErrorCode.REQUEST_ERROR,
                        HttpStatus.BAD_REQUEST,
                        "Bad request",
                        "Please check the URI format",
                        List.of(
                                new ApiSubErrorURISyntax(
                                        ex.getInput(),
                                        ex.getMessage(),
                                        ex.getReason() ) ) ) ) )
                                                .generateApiError() );
    }

    private ResponseEntity<ApiError> buildResponseEntity( ApiError apiError ) {
        return new ResponseEntity<ApiError>( apiError, apiError.getStatus() );
    }
}
