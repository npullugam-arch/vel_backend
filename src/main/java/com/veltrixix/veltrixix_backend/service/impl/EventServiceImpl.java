package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.event.EventRequest;
import com.veltrixix.veltrixix_backend.dto.event.EventResponse;
import com.veltrixix.veltrixix_backend.dto.event.EventStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.entity.Event;
import com.veltrixix.veltrixix_backend.enums.EventStatus;
import com.veltrixix.veltrixix_backend.enums.EventType;
import com.veltrixix.veltrixix_backend.exception.BadRequestException;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.mapper.EventMapper;
import com.veltrixix.veltrixix_backend.repository.EventRepository;
import com.veltrixix.veltrixix_backend.repository.RegistrationRepository;
import com.veltrixix.veltrixix_backend.service.EventService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public EventServiceImpl(
            EventRepository eventRepository,
            RegistrationRepository registrationRepository
    ) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public EventResponse createEvent(EventRequest request) {
        Event event = new Event();
        mapRequestToEntity(request, event);
        return EventMapper.toResponse(eventRepository.save(event));
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        mapRequestToEntity(request, event);
        return EventMapper.toResponse(eventRepository.save(event));
    }

    @Override
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        return EventMapper.toResponse(event);
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }

    @Override
    public List<EventResponse> getEventsByStatus(String status) {
        EventStatus eventStatus = parseEventStatus(status);
        return eventRepository.findByStatus(eventStatus)
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }

    @Override
    public List<EventResponse> getEventsByType(String eventType) {
        EventType type = parseEventType(eventType);
        return eventRepository.findByEventType(type)
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }

    @Override
    public void updateEventStatus(Long id, EventStatusUpdateRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        event.setStatus(parseEventStatus(request.getStatus()));
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        try {
            // Step 1: delete child records (registrations)
            if (registrationRepository.existsByEventId(id)) {
                registrationRepository.deleteRegistrationsByEventId(id);
            }

            // Step 2: delete event
            eventRepository.delete(event);
            eventRepository.flush();

        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException(
                    "Unable to delete this event because it is linked to other records."
            );
        }
    }

    private void mapRequestToEntity(EventRequest request, Event event) {
        event.setTitle(request.getTitle());
        event.setTopic(request.getTopic());
        event.setDomain(request.getDomain());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setSponsors(request.getSponsors());
        event.setCapacity(request.getCapacity());
        event.setEventType(parseEventType(request.getEventType()));
        event.setStatus(parseEventStatus(request.getStatus()));
        event.setEventDate(request.getEventDate());
        event.setRegistrationOpen(
                request.getRegistrationOpen() != null ? request.getRegistrationOpen() : true
        );
    }

    private EventStatus parseEventStatus(String status) {
        try {
            return EventStatus.valueOf(status.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid event status value");
        }
    }

    private EventType parseEventType(String eventType) {
        try {
            return EventType.valueOf(eventType.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid event type value");
        }
    }
}