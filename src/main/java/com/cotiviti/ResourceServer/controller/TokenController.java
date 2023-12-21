package com.cotiviti.ResourceServer.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {
    @GetMapping("/jwt")
    public Jwt getPrinciple(@AuthenticationPrincipal Jwt jwt){
        return jwt;
    }
}
