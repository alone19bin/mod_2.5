package com.maxim.spring_security_rest_api_app.rest;

import com.maxim.spring_security_rest_api_app.dto.AuthenticationRequestDto;
import com.maxim.spring_security_rest_api_app.model.User;
import com.maxim.spring_security_rest_api_app.repository.UserRepository;
import com.maxim.spring_security_rest_api_app.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j           //  логгер
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean isPasswordHashed(String password) {
        //проверка на длину хешированного  пароля
        return password != null && password.length() > 70;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        log.info("Login attempt with email: {}", requestDto.getEmail());

        try {
            log.info("Authenticating user with email: {}", requestDto.getEmail());
            log.info("Attempting to authenticate user with email: {} and password: {}", requestDto.getEmail(), requestDto.getPassword());

            // был ли пароль хеширован проверка
            if (!isPasswordHashed(requestDto.getPassword())) {
                requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword()
                    )
            );

            log.info("Authentication successful for user: {}", requestDto.getEmail());

            // Поиск пользователя в бд
            User user = userRepository.findByEmail(requestDto.getEmail())
                    .orElseThrow();

            //создание токена
            String token = jwtTokenProvider.createToken(user.getEmail(), authentication.getAuthorities());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", requestDto.getEmail());
            response.put("token", token);
            log.info("Token successfully generated for user: {}", requestDto.getEmail());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}. Reason: {}", requestDto.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid email/password combination");
        } catch (Exception e) {
            log.error("An unexpected error occurred during login for user: {}. Reason: {}", requestDto.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}







