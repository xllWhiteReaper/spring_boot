package com.xllWhiteReaper.security_v2.config.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.xllWhiteReaper.security_v2.models.User;
import com.xllWhiteReaper.security_v2.repositories.UserRepository;
import com.xllWhiteReaper.security_v2.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Get the header form the request
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer") ||
                authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Get the jwt from the authorization param
        String jwtToken = authHeader.split(" ")[1];

        // 3. Extract claims (username, password)
        String username = jwtService.extractUsername(jwtToken);

        // 4. Set an Authentication object inside the context holder
        User user = userRepository.findByUsername(username).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()); // credential not needed because the authenticate method was
                                                        // already executed by Authentication Manager
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 5. Proceed with other filters
        filterChain.doFilter(request, response);
    }
}
