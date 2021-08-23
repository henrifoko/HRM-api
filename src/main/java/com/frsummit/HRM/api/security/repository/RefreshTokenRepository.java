package com.frsummit.HRM.api.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frsummit.HRM.api.security.model.RefreshToken;
import com.frsummit.HRM.api.security.model.User;

/**
 * 
 * @author hfoko
 *
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * 
     */
    Optional<RefreshToken> findById( Long id );

    /**
     * 
     * @param token
     * @return
     */
    Optional<RefreshToken> findByToken( String token );

    /**
     * 
     * @param user
     * @return
     */
    int deleteByUser( User user );
}
