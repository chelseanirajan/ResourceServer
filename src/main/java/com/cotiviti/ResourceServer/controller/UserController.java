package com.cotiviti.ResourceServer.controller;

import com.cotiviti.ResourceServer.response.UserRest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @GetMapping("/status")
    public String getStatusOfTheServer(){
        return "OK";
    }
    //@Secured("ROLE_developer")
    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "User ID "+ id + " is deleted. With JWT subject "+jwt.getSubject();
    }
    @PostAuthorize("hasRole('ROLE_developer') or #returnObject.userId == #jwt.subject")
    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new UserRest("9ca9c720-7a53-41ec-942c-f3b2887ab580", "Nirajan", "Karki");
    }
}
