package com.frsummit.HRM.api.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frsummit.HRM.api.security.model.User;

/**
 * 
 * @author hfoko
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 
     * @param username
     * @return
     */
    Optional<User> findByUsername( String username );

    /**
     * 
     * @param username
     * @return
     */
    Boolean existsByUsername( String username );

    /**
     * 
     * @param email
     * @return
     */
    Boolean existsByEmail( String email );
}