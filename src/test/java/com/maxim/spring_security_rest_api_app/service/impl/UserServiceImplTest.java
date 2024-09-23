package com.maxim.spring_security_rest_api_app.service.impl;

import com.maxim.spring_security_rest_api_app.model.Role;
import com.maxim.spring_security_rest_api_app.model.Status;
import com.maxim.spring_security_rest_api_app.model.User;
import com.maxim.spring_security_rest_api_app.repository.UserRepository;
import com.maxim.spring_security_rest_api_app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

  /*  private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService serviceUnderTest = new UserServiceImpl(userRepository);

    private List<User> getUsers() {
        return List.of(
                new User(1L, "name_1","email_1","pass_1", new ArrayList<>(),Role.USER, LocalDateTime.now(),null, Status.ACTIVE),
                new User(2L, "name_2","email_2","pass_2", new ArrayList<>(),Role.USER, LocalDateTime.now(),null, Status.ACTIVE),
                new User(3L, "name_3","email_3","pass_3", new ArrayList<>(),Role.MODERATOR, LocalDateTime.now(),null, Status.ACTIVE),
                new User(4L, "name_4","email_4","pass_4", new ArrayList<>(),Role.ADMIN, LocalDateTime.now(),null, Status.ACTIVE)
        );
    }

    private User getUser() {
        return new User(1L, "name_1","email_1","pass_1", new ArrayList<>(),Role.ADMIN, LocalDateTime.now(),null, Status.ACTIVE);
    }

    @Test
    void getById() {
        doReturn(getUser()).when(userRepository).getById(anyLong());
       User user = serviceUnderTest.getById(1L);
        assertEquals(user, getUser());
    }

    @Test
    void getAll() {
        doReturn(getUsers()).when(userRepository.findAll());
        List<User> users = serviceUnderTest.getAll();
        assertEquals(users.size(),getUsers().size());
    }

    @Test
    void findByUsername() {
        doReturn(getUser()).when(userRepository).findByUserName(anyString());
        User user = serviceUnderTest.findByUsername("name_1");
        assertEquals(user, getUser());
    }

    @Test
    void findByEmail() {
        doReturn(getUser()).when(userRepository).findByEmail(anyString());
        Optional<User> user = serviceUnderTest.findByEmail("email_1");
        assertEquals(user, getUser());
    }

    @Test
    void create() {
        doReturn(getUser()).when(userRepository).save(getUser());
        User user = serviceUnderTest.create(getUser());
        assertEquals(user, userRepository.save(getUser()));
    }

    @Test
    void update() {
        doReturn(getUser()).when(userRepository).saveAndFlush(getUser());
        User user = serviceUnderTest.update(getUser());
        assertEquals(user, userRepository.saveAndFlush(getUser()));
    }

    @Test
    void deleteById() {
        userRepository.deleteById(anyLong());
        verify(userRepository).deleteById(anyLong());
    }*/
}