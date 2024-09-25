package com.maxim.spring_security_rest_api_app.service.impl;

import com.maxim.spring_security_rest_api_app.model.Event;
import com.maxim.spring_security_rest_api_app.model.File;
import com.maxim.spring_security_rest_api_app.model.Status;
import com.maxim.spring_security_rest_api_app.model.User;
import com.maxim.spring_security_rest_api_app.repository.EventRepository;
import com.maxim.spring_security_rest_api_app.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class EventServiceImplTest {

}