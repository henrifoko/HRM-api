package com.frsummit.HRM.api.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.security.exception.TokenRefreshException;
import com.frsummit.HRM.api.security.model.ERole;
import com.frsummit.HRM.api.security.model.RefreshToken;
import com.frsummit.HRM.api.security.model.Role;
import com.frsummit.HRM.api.security.model.User;
import com.frsummit.HRM.api.security.model.UserDetailsImpl;
import com.frsummit.HRM.api.security.model.request.LoginRequest;
import com.frsummit.HRM.api.security.model.request.SignupRequest;
import com.frsummit.HRM.api.security.model.request.TokenRefreshRequest;
import com.frsummit.HRM.api.security.model.response.JwtResponse;
import com.frsummit.HRM.api.security.model.response.MessageResponse;
import com.frsummit.HRM.api.security.model.response.TokenRefreshResponse;
import com.frsummit.HRM.api.security.repository.RoleRepository;
import com.frsummit.HRM.api.security.repository.UserRepository;
import com.frsummit.HRM.api.security.service.RefreshTokenService;
import com.frsummit.HRM.api.security.util.JwtUtils;

/**
 * 
 * @author hfoko
 *
 */
@CrossOrigin( origins = "*", maxAge = 3600 )
@RestController
@RequestMapping( "/api/auth" )
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository        userRepository;

    @Autowired
    RoleRepository        roleRepository;

    @Autowired
    RefreshTokenService   refreshTokenService;

    @Autowired
    PasswordEncoder       encoder;

    @Autowired
    JwtUtils              jwtUtils;

    /**
     * 
     * @param loginRequest
     * @return
     */
    @PostMapping( "/signin" )
    public ResponseEntity<?> authenticateUser( @Valid @RequestBody LoginRequest loginRequest ) {
        System.out.println( "AuthController - authenticateUser" );

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( loginRequest.getUsername(), loginRequest.getPassword() ) );

        SecurityContextHolder.getContext().setAuthentication( authentication );
        String jwt = jwtUtils.generateJwtToken( authentication );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map( item -> item.getAuthority() )
                .collect( Collectors.toList() );

        RefreshToken refreshToken = refreshTokenService.createRefreshToken( userDetails.getId() );

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        refreshToken,
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles ) );
    }

    /**
     * 
     * @param signupRequest
     * @return
     */
    @PostMapping( "/signup" )
    public ResponseEntity<MessageResponse> registerUser( @Valid @RequestBody SignupRequest signupRequest ) {
        System.out.println( "AuthController - registerUser" );

        if ( userRepository.existsByUsername( signupRequest.getUsername() ) ) {
            return ResponseEntity
                    .badRequest()
                    .body( new MessageResponse( "Error: Username is already taken!" ) );
        }

        if ( userRepository.existsByEmail( signupRequest.getEmail() ) ) {
            return ResponseEntity
                    .badRequest()
                    .body( new MessageResponse( "Error: Email is already in use!" ) );
        }

        // Create new user's account
        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode( signupRequest.getPassword() ) );

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<Role>();

        if ( strRoles == null ) {
            Role userRole = roleRepository
                    .findByName( ERole.ROLE_USER )
                    .orElseThrow( () -> new RuntimeException( "Error: Role is not found." ) );
            roles.add( userRole );
        } else {
            strRoles.forEach( role -> {
                switch ( role ) {
                case "admin":
                    Role adminRole = roleRepository
                            .findByName( ERole.ROLE_ADMIN )
                            .orElseThrow( () -> new RuntimeException( "Error: Role is not found." ) );
                    roles.add( adminRole );
                    break;

                case "moderator":
                    Role modRole = roleRepository
                            .findByName( ERole.ROLE_MODERATOR )
                            .orElseThrow( () -> new RuntimeException( "Error: Role is not found." ) );
                    roles.add( modRole );

                default:
                    Role userRole = roleRepository
                            .findByName( ERole.ROLE_USER )
                            .orElseThrow( () -> new RuntimeException( "Error: Role is not found." ) );
                    roles.add( userRole );
                }
            } );
        }

        user.setRoles( roles );
        userRepository.save( user );

        return ResponseEntity.ok( new MessageResponse( "User registered successfully" ) );
    }

    /**
     * 
     * @param request
     * @return
     */
    @PostMapping( "/refreshtoken" )
    public ResponseEntity<?> refreshToken( @Valid @RequestBody TokenRefreshRequest request ) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken( requestRefreshToken )
                .map( refreshTokenService::verifyExpiration )
                .map( RefreshToken::getUser )
                .map( user -> {
                    String token = jwtUtils.generateTokenFromUsername( user.getUsername() );
                    return ResponseEntity.ok( new TokenRefreshResponse( token, requestRefreshToken ) );
                } )
                .orElseThrow( () -> new TokenRefreshException( requestRefreshToken, "Refresh was not found." ) );
    }
}
