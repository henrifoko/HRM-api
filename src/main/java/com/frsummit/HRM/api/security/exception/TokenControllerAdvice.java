package com.frsummit.HRM.api.security.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.frsummit.HRM.api.security.model.response.ErrorMessage;

/**
 * 
 * @author hfoko
 *
 */
@RestControllerAdvice
public class TokenControllerAdvice {

    /**
     * 
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler( value = TokenRefreshException.class )
    @ResponseStatus( HttpStatus.FORBIDDEN )
    public ErrorMessage handleTokenRefreshException( TokenRefreshException e, WebRequest request ) {

        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                e.getMessage(),
                request.getDescription( false ) );
    }
}
