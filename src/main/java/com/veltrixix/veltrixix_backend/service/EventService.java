package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.event.EventRequest;
import com.veltrixix.veltrixix_backend.dto.event.EventResponse;
import com.veltrixix.veltrixix_backend.dto.event.EventStatusUpdateRequest;

import java.util.List;

public interface EventService {

    EventResponse createEvent(EventRequest request);

    EventResponse updateEvent(Long id, EventRequest request);

    EventResponse getEventById(Long id);

    List<EventResponse> getAllEvents();

    List<EventResponse> getEventsByStatus(String status);

    List<EventResponse> getEventsByType(String eventType);

    void updateEventStatus(Long id, EventStatusUpdateRequest request);

    void deleteEvent(Long id);
}