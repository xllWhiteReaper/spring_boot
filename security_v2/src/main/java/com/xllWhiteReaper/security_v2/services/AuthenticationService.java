package com.xllWhiteReaper.security_v2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.xllWhiteReaper.security_v2.dto.AuthenticationRequest;
import com.xllWhiteReaper.security_v2.dto.AuthenticationResponse;
import com.xllWhiteReaper.security_v2.models.User;
import com.xllWhiteReaper.security_v2.repositories.UserRepository;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        System.out.println("Inside login from authentication service");
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword());

        authenticationManager.authenticate(authToken); // bad error handling, it causes a 403 code when it was a 404
        User user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwt = jwtService.getToken(user, getExtraClaims(user));
        System.out.println("Inside login from authentication service");
        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> getExtraClaims(User user) {
        return new HashMap<String, Object>(Map.of(
                "name", user.getName(),
                "role", user.getRole().name(),
                "permission", user.getAuthorities()));
    }
}
