package com.xllWhiteReaper.security_v2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xllWhiteReaper.security_v2.dto.AuthenticationRequest;
import com.xllWhiteReaper.security_v2.dto.AuthenticationResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return null;
    }
}
