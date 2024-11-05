package com.maxim.spring_security_rest_api_app.service.impl;

import com.maxim.spring_security_rest_api_app.config.SecurityConfig;
import com.maxim.spring_security_rest_api_app.model.Status;
import com.maxim.spring_security_rest_api_app.model.User;
import com.maxim.spring_security_rest_api_app.repository.UserRepository;
import com.maxim.spring_security_rest_api_app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void hashPasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String rawPassword = user.getPassword();
            if (rawPassword != null && !passwordEncoder.matches(rawPassword, user.getPassword())) {
                String encodedPassword = passwordEncoder.encode(rawPassword);
                log.info("User: {}, Raw Password: {}, Encoded Password: {}", user.getEmail(), rawPassword, encodedPassword);
                user.setPassword(encodedPassword);
                userRepository.save(user);
            }
        }
    }

    public User saveUser(User user) {
        log.info("Raw password: {}", user.getPassword());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        log.info("Hashed password: {}", hashedPassword);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Хеширование пароля
        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.warn("FindById - no User found by id: {}", id);
            return new UsernameNotFoundException("User not found");
        });
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("GetAll: {} Users found", users.size());
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            log.warn("FindByName - no User found by name: {}", username);
        }
        log.info("Find User by name: {}", username);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            log.warn("FindByEmail - no User found by email: {}", email);
        }
        log.info("Find User by email: {}", email);
        return user;
    }

    @Override
    public User create(User user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail()); // добавляем email в сохранение
        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // хешируем пароль
        newUser.setRole(user.getRole());
        newUser.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(newUser);
        log.info("Create - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public User update(User user) {
        log.info("Update - User: {}", user);
        user.setStatus(Status.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("DeleteById - no User found by id: {}", id);
        } else {
            log.info("Delete User by id: {}", id);
            user.setStatus(Status.DELETED);
            userRepository.save(user);
        }
    }


}





