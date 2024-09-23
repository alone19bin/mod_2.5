package com.maxim.spring_security_rest_api_app.repository;

import com.maxim.spring_security_rest_api_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByUserName(String username);
}






