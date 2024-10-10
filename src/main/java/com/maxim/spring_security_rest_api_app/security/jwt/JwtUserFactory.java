package com.maxim.spring_security_rest_api_app.security.jwt;

import com.maxim.spring_security_rest_api_app.model.Role;
import com.maxim.spring_security_rest_api_app.model.Status;
import com.maxim.spring_security_rest_api_app.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus() == Status.ACTIVE, //    Проверяем, активен ли пользователь
                user.getUpdated(),
                mapToGrantedAuthorities(Collections.singletonList(user.getRole())) //используем список ролей
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }
}

