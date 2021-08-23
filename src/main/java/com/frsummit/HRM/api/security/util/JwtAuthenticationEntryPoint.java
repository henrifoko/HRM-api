package com.frsummit.HRM.api.security.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hfoko
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger( JwtAuthenticationEntryPoint.class );

    /**
     * 
     */
    @Override
    public void commence( HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException ) throws IOException, ServletException {
        System.out.println( "JwtAuthenticationEntryPoint - commence" );

        LOGGER.error( "Unauthorized error : {}", authException.getMessage() );
        response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized" );
    }

}
