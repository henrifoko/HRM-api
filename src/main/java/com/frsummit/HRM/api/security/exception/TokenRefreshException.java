package com.frsummit.HRM.api.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author hfoko
 *
 */
@ResponseStatus( code = HttpStatus.FORBIDDEN )
public class TokenRefreshException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param token
     * @param message
     */
    public TokenRefreshException( String token, String message ) {
        super( String.format( "Failed for [%s]: %s", token, message ) );
    }
}
