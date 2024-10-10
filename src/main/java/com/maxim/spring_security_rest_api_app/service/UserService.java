package com.maxim.spring_security_rest_api_app.service;

import com.maxim.spring_security_rest_api_app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GenericService<User,Long> {
    User create(User user);

    User update(User user);

    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    void hashPasswords();

    User saveUser(User user);
}

