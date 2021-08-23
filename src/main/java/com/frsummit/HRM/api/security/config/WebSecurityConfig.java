package com.frsummit.HRM.api.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.frsummit.HRM.api.generic.model.ApiEpconfig;
import com.frsummit.HRM.api.generic.model.ApiResource;
import com.frsummit.HRM.api.generic.model.EndpointConfig;
import com.frsummit.HRM.api.generic.model.EndpointConfigSecurity;
import com.frsummit.HRM.api.generic.service.EndpointService;
import com.frsummit.HRM.api.security.filter.AuthenticationTokenFilter;
import com.frsummit.HRM.api.security.model.Role;
import com.frsummit.HRM.api.security.service.UserDetailsServiceImpl;
import com.frsummit.HRM.api.security.util.JwtAuthenticationEntryPoint;
import com.frsummit.HRM.api.util.Util;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl              userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private EndpointService             endpointService;

    @Bean
    public AuthenticationTokenFilter authenticationJwtTokenFilter() {
        System.out.println( "WebSecurityConfig - authenticationJwtTokenFilter" );

        return new AuthenticationTokenFilter();
    }

    @Override
    public void configure( AuthenticationManagerBuilder authenticationManagerBuilder ) throws Exception {
        System.out.println( "WebSecurityConfig - configure 1" );

        authenticationManagerBuilder.userDetailsService( userDetailsService ).passwordEncoder( passwordEncoder() );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        System.out.println( "WebSecurityConfig - authenticationManagerBean" );

        return super.authenticationManagerBean();
    }

    /**
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println( "WebSecurityConfig - passwordEncoder" );

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        System.out.println( "WebSecurityConfig - configure 2" );

        ApiEpconfig epconfig = endpointService.getEpconfig();

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry interceptor = http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint( unauthorizedHandler )
                .and()
                .sessionManagement()
                .sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                .and()
                .authorizeRequests();

        // Inject a loop
        for ( ApiResource res : epconfig.getRessources() ) {
            for ( EndpointConfig e : res.getEndpoints() ) {
                EndpointConfigSecurity security = e.getSecurity();
                String uri = endpointService.generateEndpointUri( e, res );
                if ( security.isAuthenticated() ) {
                    List<Role> roleList = security.getRoles();
                    if ( !roleList.isEmpty() ) {
                        String[] roles = Arrays.copyOf(
                                security.getRoles()
                                        .stream()
                                        .map( r -> r.getName().toString() )
                                        .toArray(),
                                roleList.size(),
                                String[].class );
                        interceptor = interceptor
                                .antMatchers( Util.withoutEndingSlash( uri ) )
                                .hasAnyAuthority( roles );
                        interceptor = interceptor
                                .antMatchers( Util.withEndingSlash( uri ) )
                                .hasAnyAuthority( roles );
                    } else {
                        interceptor = interceptor
                                .antMatchers( Util.withoutEndingSlash( uri ) )
                                .authenticated();
                        interceptor = interceptor
                                .antMatchers( Util.withEndingSlash( uri ) )
                                .authenticated();
                    }
                } else {
                    interceptor = interceptor
                            .antMatchers( Util.withoutEndingSlash( uri ) )
                            .permitAll();
                    interceptor = interceptor
                            .antMatchers( Util.withEndingSlash( uri ) )
                            .permitAll();
                }
            }
        }

        interceptor = interceptor
                .antMatchers( "/api/auth/**" ).permitAll();
        // .antMatchers( "/api/users/" ).permitAll()
        // .antMatchers( "/api/users" ).hasRole( "ADMIN" )
        // .antMatchers( "/api/v1/test/foo" ).permitAll();

        // End the loop
        interceptor.anyRequest().authenticated();

        http.addFilterBefore( authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class );
    }
}