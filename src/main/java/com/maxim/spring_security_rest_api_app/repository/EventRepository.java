package com.maxim.spring_security_rest_api_app.repository;

import com.maxim.spring_security_rest_api_app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
