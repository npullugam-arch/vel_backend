package com.veltrixix.veltrixix_backend.mapper;

import com.veltrixix.veltrixix_backend.dto.event.EventResponse;
import com.veltrixix.veltrixix_backend.entity.Event;

public final class EventMapper {

    private EventMapper() {
    }

    public static EventResponse toResponse(Event event) {
        if (event == null) {
            return null;
        }

        EventResponse response = new EventResponse();
        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setTopic(event.getTopic());
        response.setDomain(event.getDomain());
        response.setDescription(event.getDescription());
        response.setLocation(event.getLocation());
        response.setSponsors(event.getSponsors());
        response.setCapacity(event.getCapacity());
        response.setEventType(event.getEventType() != null ? event.getEventType().name() : null);
        response.setStatus(event.getStatus() != null ? event.getStatus().name() : null);
        response.setEventDate(event.getEventDate());
        response.setRegistrationOpen(event.getRegistrationOpen());
        response.setCreatedAt(event.getCreatedAt());
        response.setUpdatedAt(event.getUpdatedAt());
        return response;
    }
}