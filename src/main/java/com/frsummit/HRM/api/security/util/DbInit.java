package com.frsummit.HRM.api.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.frsummit.HRM.api.security.model.ERole;
import com.frsummit.HRM.api.security.model.Role;
import com.frsummit.HRM.api.security.repository.RoleRepository;

/**
 * 
 * @author hfoko
 *
 */
@Component
public class DbInit implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    /**
     * 
     */
    @Override
    public void run( String... args ) throws Exception {
        System.out.println( "DbInit - run" );

        Role userRole = new Role();
        Role modRole = new Role();
        Role adminRole = new Role();

        userRole.setName( ERole.ROLE_USER );
        modRole.setName( ERole.ROLE_MODERATOR );
        adminRole.setName( ERole.ROLE_ADMIN );

        // roleRepository.saveAll(Arrays.asList(userRole, modRole, adminRole));
    }
}
