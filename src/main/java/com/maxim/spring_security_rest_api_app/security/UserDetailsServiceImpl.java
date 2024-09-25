package com.maxim.spring_security_rest_api_app.security;

import com.maxim.spring_security_rest_api_app.model.User;
import com.maxim.spring_security_rest_api_app.security.jwt.JwtUser;
import com.maxim.spring_security_rest_api_app.security.jwt.JwtUserFactory;
import com.maxim.spring_security_rest_api_app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}

