package com.xllWhiteReaper.security_v2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.xllWhiteReaper.security_v2.utils.Permission;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

        // @Autowired
        // private AuthenticationFilter authenticationFilter;

        @Autowired
        private AuthenticationProvider authenticationProvider; // Dao authentication provider

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
                return httpSec
                                .csrf(csrfConfig -> csrfConfig.disable()) // cross site request forgery makes
                                                                          // requests by using the
                                                                          // user credentials, however, we disable this
                                                                          // as we are going
                                                                          // to work with jwt tokens
                                .sessionManagement(sessionManagementConfig -> sessionManagementConfig
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // In order to
                                                                                                         // avoid
                                                                                                         // ram overhead
                                                                                                         // on
                                                                                                         // the server
                                                                                                         // by making
                                                                                                         // each request
                                                                                                         // independent
                                                                                                         // of others
                                .authenticationProvider(authenticationProvider)
                                // .addFilterBefore(authenticationFilter,
                                // UsernamePasswordAuthenticationFilter.class)
                                .authorizeHttpRequests(authConfig -> {
                                        // public ones
                                        authConfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll(); // login
                                                                                                                // must
                                                                                                                // not
                                                                                                                // be
                                                                                                                // secured
                                        authConfig.requestMatchers("/error").permitAll();

                                        // private ones
                                        authConfig.requestMatchers(HttpMethod.GET, "/products")
                                                        .hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                                        authConfig.requestMatchers(HttpMethod.POST, "/products")
                                                        .hasAuthority(Permission.SAVE_ONE_PRODUCT.name());

                                        // all the other ones
                                        authConfig.anyRequest().denyAll();
                                })
                                .build();
        }
}
