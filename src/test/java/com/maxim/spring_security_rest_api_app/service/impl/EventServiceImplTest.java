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
  /*  private EventRepository eventRepository = Mockito.mock(EventRepository.class);
    private EventService serviceUnderTest = new EventServiceImpl(eventRepository);

    private List<Event> getEvents() {
        return List.of(
                new Event(1L, new User(), new File(), LocalDateTime.now(),null, Status.ACTIVE),
                new Event(2L, new User(), new File(), LocalDateTime.now(),null, Status.ACTIVE),
                new Event(3L, new User(), new File(), LocalDateTime.now(),null, Status.ACTIVE),
                new Event(4L, new User(), new File(), LocalDateTime.now(),null, Status.ACTIVE)
        );
    }

    private Event getEvent() {
        return new Event(1L, new User(), new File(), LocalDateTime.now(),null,Status.ACTIVE);
    }

    @Test
    void getById() {
        doReturn(getEvent()).when(eventRepository).getById(anyLong());
        Event event = serviceUnderTest.getById(1L);
        assertEquals(event, getEvent());
    }

    @Test
    void getAll() {
        doReturn(getEvents()).when(eventRepository.findAll());
        List<Event> events = serviceUnderTest.getAll();
        assertEquals(events.size(),getEvents().size());
    }

    @Test
    void create() {
        doReturn(getEvent()).when(eventRepository).save(getEvent());
        Event event = serviceUnderTest.create(getEvent());
        assertEquals(event, eventRepository.save(getEvent()));
    }

    @Test
    void update() {
        doReturn(getEvent()).when(eventRepository).saveAndFlush(getEvent());
        Event event = serviceUnderTest.update(getEvent());
        assertEquals(event, eventRepository.saveAndFlush(getEvent()));
    }

    @Test
    void deleteById() {
        eventRepository.deleteById(anyLong());
        verify(eventRepository).deleteById(anyLong());
    }*/
}