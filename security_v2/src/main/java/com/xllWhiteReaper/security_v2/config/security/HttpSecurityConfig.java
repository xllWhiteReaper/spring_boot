package com.xllWhiteReaper.security_v2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private HttpSecurity httpSec;

    @Autowired
    AuthenticationProvider authenticationProvider; // Dao authentication provider

    @Bean
    public SecurityFilterChain securityFilterChain() throws Exception {
        return httpSec
                .csrf(csrfConfig -> csrfConfig.disable()) // cross site request forgery makes requests by using the
                                                          // user credentials, however, we disable this as we are going
                                                          // to work with jwt tokens
                .sessionManagement(sessionManagementConfig -> sessionManagementConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // In order to avoid ram overhead on
                                                                                 // the server by making each request
                                                                                 // independent of others
                .authenticationProvider(authenticationProvider)
                .build();
    }

}
