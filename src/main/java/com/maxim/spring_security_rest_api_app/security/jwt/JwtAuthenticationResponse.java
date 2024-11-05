package com.maxim.spring_security_rest_api_app.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String token; //   храним JWTтокен
}