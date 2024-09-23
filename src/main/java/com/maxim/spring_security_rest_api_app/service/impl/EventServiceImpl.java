package com.maxim.spring_security_rest_api_app.service.impl;

import com.maxim.spring_security_rest_api_app.model.Event;
import com.maxim.spring_security_rest_api_app.model.Status;
import com.maxim.spring_security_rest_api_app.repository.EventRepository;
import com.maxim.spring_security_rest_api_app.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            log.warn("FindById - no Event find by id: {}", id);
        }
        log.info("Find Event by id: {}", id);
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = eventRepository.findAll();
        log.info("GetAll: {} Events found", events.size());
        return events;
    }

    @Override
    public Event create(Event event) {
        log.info("Create Event: {}", event);
        event.setStatus(Status.ACTIVE);
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        log.info("Update User: {}", event);
        event.setStatus(Status.ACTIVE);
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            log.warn("DeleteById - no Event find by id: {}", id);
        } else {
            log.info("Delete Event by id: {}", id);
            event.setStatus(Status.DELETED);
            eventRepository.save(event);
        }
    }
}
