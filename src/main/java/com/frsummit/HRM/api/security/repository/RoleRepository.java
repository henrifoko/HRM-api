package com.frsummit.HRM.api.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frsummit.HRM.api.security.model.ERole;
import com.frsummit.HRM.api.security.model.Role;

/**
 * 
 * @author hfoko
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    /**
     * 
     * @param name
     * @return
     */
    Optional<Role> findByName( ERole name );
}
