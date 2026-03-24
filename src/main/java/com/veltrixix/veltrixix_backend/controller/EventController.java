package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.dto.event.EventRequest;
import com.veltrixix.veltrixix_backend.dto.event.EventResponse;
import com.veltrixix.veltrixix_backend.dto.event.EventStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.service.EventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ApiResponse<EventResponse> createEvent(@Valid @RequestBody EventRequest request) {
        return ApiResponse.success("Event created successfully", eventService.createEvent(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<EventResponse> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request
    ) {
        return ApiResponse.success("Event updated successfully", eventService.updateEvent(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<EventResponse> getEventById(@PathVariable Long id) {
        return ApiResponse.success(eventService.getEventById(id));
    }

    @GetMapping
    public ApiResponse<List<EventResponse>> getAllEvents() {
        return ApiResponse.success(eventService.getAllEvents());
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<EventResponse>> getEventsByStatus(@PathVariable String status) {
        return ApiResponse.success(eventService.getEventsByStatus(status));
    }

    @GetMapping("/type/{eventType}")
    public ApiResponse<List<EventResponse>> getEventsByType(@PathVariable String eventType) {
        return ApiResponse.success(eventService.getEventsByType(eventType));
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<MessageResponse> updateEventStatus(
            @PathVariable Long id,
            @Valid @RequestBody EventStatusUpdateRequest request
    ) {
        eventService.updateEventStatus(id, request);
        return ApiResponse.success("Event status updated successfully", MessageResponse.of("Event status updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MessageResponse> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ApiResponse.success("Event deleted successfully", MessageResponse.of("Event deleted successfully"));
    }
}