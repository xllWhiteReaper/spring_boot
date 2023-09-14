package com.xllWhiteReaper.security_v2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xllWhiteReaper.security_v2.dto.AuthenticationRequest;
import com.xllWhiteReaper.security_v2.dto.AuthenticationResponse;
import com.xllWhiteReaper.security_v2.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest authenticationRequest) {
        System.out.println("Logging in");
        AuthenticationResponse jwtDto = authenticationService.login(authenticationRequest);
        System.out.println("jwtDto");
        System.out.println(jwtDto);

        return ResponseEntity.ok(jwtDto);
    }
}
