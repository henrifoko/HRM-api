package com.frsummit.HRM.api.security.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.frsummit.HRM.api.security.model.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 
 * @author hfoko
 *
 */
@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger( JwtUtils.class );

    @Value( "${hfk.app.jwt.secret}" )
    private String              JWT_SECRET;

    @Value( "${hfk.app.jwt.expiration-ms}" )
    private int                 JWT_EXPIRATION_MS;

    /**
     * 
     * @param auth
     * @return
     */
    public String generateJwtToken( Authentication auth ) {
        System.out.println( "JwtUtils - generateJwtToken" );

        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        return generateTokenFromUsername( principal.getUsername() );
    }

    /**
     * 
     * @param username
     * @return
     */
    public String generateTokenFromUsername( String username ) {
        System.out.println( "JwtUtils - generateTokenFromUsername" );

        return Jwts.builder()
                .setSubject( username )
                .setIssuedAt( new Date() )
                .setExpiration( new Date( new Date().getTime() + JWT_EXPIRATION_MS ) )
                .signWith( SignatureAlgorithm.HS512, JWT_SECRET )
                .compact();
    }

    /**
     * 
     */
    public String getUsernameFromJwtToken( String token ) {
        System.out.println( "JwtUtils - getUsernameFromJwtToken" );

        return Jwts.parser().setSigningKey( JWT_SECRET ).parseClaimsJws( token ).getBody().getSubject();
    }

    /**
     * 
     * @param authToken
     * @return
     */
    public boolean validateJwtToken( String authToken ) {
        System.out.println( "JwtUtils - validateJwtToken" );

        try {
            Jwts.parser().setSigningKey( JWT_SECRET ).parseClaimsJws( authToken );
            return true;
        } catch ( SignatureException e ) {
            LOGGER.error( "Invalid JWT signature : {}", e.getMessage() );
        } catch ( MalformedJwtException e ) {
            LOGGER.error( "Invalid JWT token : {}", e.getMessage() );
        } catch ( ExpiredJwtException e ) {
            LOGGER.error( "JWT token is expired: {}", e.getMessage() );
        } catch ( UnsupportedJwtException e ) {
            LOGGER.error( "JWT token is unsupported: {}", e.getMessage() );
        } catch ( IllegalArgumentException e ) {
            LOGGER.error( "JWT claims string is empty: {}", e.getMessage() );
        }

        return false;
    }

}
