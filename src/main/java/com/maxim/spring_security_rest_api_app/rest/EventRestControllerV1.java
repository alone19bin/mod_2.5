package com.maxim.spring_security_rest_api_app.rest;

import com.maxim.spring_security_rest_api_app.dto.EventDto;
import com.maxim.spring_security_rest_api_app.model.Event;
import com.maxim.spring_security_rest_api_app.repository.EventRepository;
import com.maxim.spring_security_rest_api_app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/events/")
public class EventRestControllerV1 {

    private final EventService eventService;
    private final EventRepository eventRepository;

@Autowired
    public EventRestControllerV1(EventService eventService, EventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        Event event = eventService.getById(id);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        EventDto result = EventDto.fromEvent(event);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getAll() {
        List<Event> eventList = eventService.getAll();
        if (eventList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EventDto> eventDtoList = EventDto.toEventDtos(eventList);
        return new ResponseEntity(eventDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> save(@RequestBody Event event) {
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Event eventResult = eventService.create(event);
        return new ResponseEntity(eventResult, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event event) {
        if (id == null || event == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Event eventResult = eventService.update(event);
        return new ResponseEntity(eventResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Event event = this.eventService.getById(id);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        eventService.deleteById(id);
        return new ResponseEntity(event, HttpStatus.OK);
    }
}
