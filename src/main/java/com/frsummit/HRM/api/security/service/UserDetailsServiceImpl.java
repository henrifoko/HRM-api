package com.frsummit.HRM.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.security.model.User;
import com.frsummit.HRM.api.security.model.UserDetailsImpl;
import com.frsummit.HRM.api.security.repository.UserRepository;

/**
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * 
     */
    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        System.out.println( "UserDetailsServiceImpl - loadUserByUsername" );

        User user = userRepository.findByUsername( username ).orElseThrow(
                () -> new UsernameNotFoundException(
                        "The username '"
                                + username
                                + "' was not found" ) );

        return UserDetailsImpl.build( user );
    }

}
