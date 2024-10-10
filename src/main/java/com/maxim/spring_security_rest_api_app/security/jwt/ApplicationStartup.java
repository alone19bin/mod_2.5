package com.maxim.spring_security_rest_api_app.security.jwt;

import com.maxim.spring_security_rest_api_app.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


    @Component
    public class ApplicationStartup {

        private final UserService userService;

        @Autowired
        public ApplicationStartup(UserService userService) {
            this.userService = userService;
        }

        @PostConstruct
        public void init() {
            userService.hashPasswords();      // Хешируем   пароли при старте приложения
        }
    }

