package com.maxim.spring_security_rest_api_app.security;

import com.maxim.spring_security_rest_api_app.model.User;
import com.maxim.spring_security_rest_api_app.security.jwt.JwtUser;
import com.maxim.spring_security_rest_api_app.security.jwt.JwtUserFactory;
import com.maxim.spring_security_rest_api_app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Lazy
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //поиск пльзователя по email
        User user = userService.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User with email: {} not found", email);
                    return new UsernameNotFoundException("User with email: " + email + " not found");
                });

        // Логируем успешную загрузку  пользователя
        log.info("loadUserByUsername - user with email: {} successfully loaded", email);

        // ссоздаем и возвращаем объект JwtUser
        return JwtUserFactory.create(user);
    }
}

