package com.frsummit.HRM.api.security.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.security.exception.TokenRefreshException;
import com.frsummit.HRM.api.security.model.RefreshToken;
import com.frsummit.HRM.api.security.repository.RefreshTokenRepository;
import com.frsummit.HRM.api.security.repository.UserRepository;

/**
 * 
 * @author hfoko
 *
 */
@Service
public class RefreshTokenService {

    @Value( "${hfk.app.jwt.refresh-token-duration-ms}" )
    private Long                   refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository         userRepository;

    /**
     * 
     * @param token
     * @return
     */
    public Optional<RefreshToken> findByToken( String token ) {
        return refreshTokenRepository.findByToken( token );
    }

    /**
     * 
     * @param userId
     * @return
     */
    public RefreshToken createRefreshToken( Long userId ) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setExpiryDate( Instant.now().plusMillis( refreshTokenDurationMs ) );
        refreshToken.setUser( userRepository.findById( userId ).get() );
        refreshToken.setToken( UUID.randomUUID().toString() );

        refreshTokenRepository.save( refreshToken );
        return refreshToken;
    }

    /**
     * 
     * @param token
     * @return
     */
    public RefreshToken verifyExpiration( RefreshToken token ) {
        if ( token.getExpiryDate().compareTo( Instant.now() ) < 0 ) {
            refreshTokenRepository.delete( token );
            throw new TokenRefreshException( token.getToken(),
                    "Refresh token was expired please make a new sign in request" );
        }

        return token;
    }

    @Transactional
    public int deleteByUserId( Long userId ) {
        return refreshTokenRepository.deleteByUser( userRepository.findById( userId ).get() );
    }

}
