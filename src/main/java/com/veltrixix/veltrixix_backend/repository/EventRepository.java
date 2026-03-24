package com.veltrixix.veltrixix_backend.repository;

import com.veltrixix.veltrixix_backend.entity.Event;
import com.veltrixix.veltrixix_backend.enums.EventStatus;
import com.veltrixix.veltrixix_backend.enums.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(EventStatus status);
    List<Event> findByEventType(EventType eventType);
}