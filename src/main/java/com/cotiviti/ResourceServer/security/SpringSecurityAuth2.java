package com.cotiviti.ResourceServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityAuth2{


    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyClockRoleConverter());
        httpSecurity
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/status")
//                        .hasAuthority("SCOPE_profile")
                        .hasRole("developer")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(outh1 -> outh1.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return httpSecurity.build();
    }
}
