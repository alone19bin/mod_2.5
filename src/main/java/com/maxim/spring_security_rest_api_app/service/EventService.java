package com.maxim.spring_security_rest_api_app.service;

import com.maxim.spring_security_rest_api_app.model.Event;

public interface EventService extends GenericService<Event, Long>{
    Event create(Event event);

    Event update(Event event);

}
