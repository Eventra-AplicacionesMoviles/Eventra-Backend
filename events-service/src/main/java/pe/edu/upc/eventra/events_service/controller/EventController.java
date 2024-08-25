package pe.edu.upc.eventra.events_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.events_service.model.dtos.EventRequest;
import pe.edu.upc.eventra.events_service.model.dtos.EventResponse;
import pe.edu.upc.eventra.events_service.service.EventService;

import java.util.List;


@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(name = "EventController", description = "API for event operations")
public class EventController {

    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new event", description = "Adds a new event to the system")
    public EventResponse addEvent(@RequestBody EventRequest eventRequest) {
        return eventService.addEvent(eventRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get event by ID", description = "Retrieves a specific event by ID")
    public EventResponse getEventById(@PathVariable("id") long id) {
        return eventService.getEventById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all events", description = "Retrieves a list of all events")
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get events by title", description = "Retrieves a list of events by title")
    public List<EventResponse> getEventsByTitle(@PathVariable("title") String title) {
        return eventService.getEventsByTitle(title);
    }

    @GetMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get events by category", description = "Retrieves a list of events by category")
    public List<EventResponse> getEventsByCategory(@PathVariable("categoryId") Long categoryId) {
        return eventService.getEventsByCategory(categoryId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an event", description = "Updates a specific event by their ID")
    public void updateEvent(@PathVariable("id") long id, @RequestBody EventRequest eventRequest) {
        eventService.updateEvent(id, eventRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an event", description = "Deletes a specific event by their ID")
    public void deleteEvent(@PathVariable("id") long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get events by user ID", description = "Retrieves a list of events by user ID")
    public List<EventResponse> getEventsByUserId(@PathVariable("userId") Long userId) {
        return eventService.getEventsByUserId(userId);
    }
}