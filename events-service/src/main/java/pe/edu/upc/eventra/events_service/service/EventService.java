package pe.edu.upc.eventra.events_service.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.events_service.model.dtos.CategoryEventResponse;
import pe.edu.upc.eventra.events_service.model.dtos.EventRequest;
import pe.edu.upc.eventra.events_service.model.dtos.EventResponse;
import pe.edu.upc.eventra.events_service.model.dtos.auxs.UserResponse;
import pe.edu.upc.eventra.events_service.model.entities.CategoryEvent;
import pe.edu.upc.eventra.events_service.model.entities.Event;
import pe.edu.upc.eventra.events_service.repository.CategoryEventRepository;
import pe.edu.upc.eventra.events_service.repository.EventRepository;
import pe.edu.upc.eventra.events_service.repository.UserClient;
import pe.edu.upc.eventra.events_service.shared.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    private final EventRepository eventRepository;
    private final CategoryEventRepository categoryEventRepository;
    private final UserClient userClient;

    @Transactional
    public EventResponse addEvent(EventRequest eventRequest) {
        UserResponse organizer = userClient.getUserById(eventRequest.getOrganizerId());

        CategoryEvent categoryEvent = categoryEventRepository.findById(eventRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryEvent not found with id: " + eventRequest.getCategoryId()));

        Event event = Event.builder()
                .title(eventRequest.getTitle())
                .description(eventRequest.getDescription())
                .startDate(eventRequest.getStartDate())
                .endDate(eventRequest.getEndDate())
                .location(eventRequest.getLocation())
                .organizerId(organizer.getUserId())
                .categoryEvent(categoryEvent)
                .url(eventRequest.getUrl()) // Nuevo campo agregado
                .build();

        Event savedEvent = eventRepository.save(event);
        log.info("Event added: {}", savedEvent);
        return mapToEventResponse(savedEvent);
    }
    public List<EventResponse> getEventsByUserId(Long userId) {
        List<Event> events = eventRepository.findByOrganizerId(userId);
        return events.stream()
                .map(this::mapToEventResponse)
                .collect(Collectors.toList());
    }
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::mapToEventResponse)
                .collect(Collectors.toList());
    }

    public EventResponse getEventById(long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        return mapToEventResponse(event);
    }

    public List<EventResponse> getEventsByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::mapToEventResponse)
                .collect(Collectors.toList());
    }

    public List<EventResponse> getEventsByCategory(Long categoryId) {
        return eventRepository.findByCategoryEventId(categoryId).stream()
                .map(this::mapToEventResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventResponse updateEvent(long id, EventRequest eventRequest) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        // Validate the organizerId by calling the User service
        userClient.getUserById(eventRequest.getOrganizerId());
        CategoryEvent categoryEvent = categoryEventRepository.findById(eventRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryEvent not found with id: " + eventRequest.getCategoryId()));

        event.setTitle(eventRequest.getTitle());
        event.setDescription(eventRequest.getDescription());
        event.setStartDate(eventRequest.getStartDate());
        event.setEndDate(eventRequest.getEndDate());
        event.setLocation(eventRequest.getLocation());
        event.setOrganizerId(eventRequest.getOrganizerId()); // Set the validated user ID
        event.setCategoryEvent(categoryEvent);
        event.setUrl(eventRequest.getUrl()); // Nuevo campo agregado

        Event updatedEvent = eventRepository.save(event);
        log.info("Updated Event: {}", updatedEvent);
        return mapToEventResponse(updatedEvent);
    }

    public void deleteEvent(long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
        log.info("Deleted Event with id: {}", id);
    }

    private EventResponse mapToEventResponse(Event event) {
        UserResponse organizer;
        try {
            organizer = userClient.getUserById(event.getOrganizerId());
        } catch (FeignException e) {
            log.error("User service is unavailable, unable to fetch organizer details", e);
            organizer = UserResponse.builder()
                    .userId(null)
                    .firstName(null)
                    .lastName(null)
                    .email(null)
                    .typeOfUser(null)
                    .build();
        }
        CategoryEventResponse categoryResponse = CategoryEventResponse.builder()
                .id(event.getCategoryEvent().getId())
                .name(event.getCategoryEvent().getName())
                .build();

        // Now include UserResponse in the EventResponse
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .location(event.getLocation())
                .organizer(organizer)
                .categoryEvent(categoryResponse)
                .url(event.getUrl()) // Nuevo campo agregado
                .build();
    }
}
