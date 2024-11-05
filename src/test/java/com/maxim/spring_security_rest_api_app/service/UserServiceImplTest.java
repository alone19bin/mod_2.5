package com.maxim.spring_security_rest_api_app.service;

import com.maxim.spring_security_rest_api_app.model.User;
import com.maxim.spring_security_rest_api_app.repository.UserRepository;
import com.maxim.spring_security_rest_api_app.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setUserName("testUser");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.create(user);
        assertNotNull(createdUser);
        assertEquals("test@example.com", createdUser.getEmail());
        verify(userRepository).save(any(User.class));
    }
}