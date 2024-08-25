package pe.edu.upc.eventra.events_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upc.eventra.events_service.model.dtos.CategoryEventRequest;
import pe.edu.upc.eventra.events_service.model.dtos.CategoryEventResponse;
import pe.edu.upc.eventra.events_service.model.entities.CategoryEvent;
import pe.edu.upc.eventra.events_service.repository.CategoryEventRepository;
import pe.edu.upc.eventra.events_service.shared.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryEventService {
    private final CategoryEventRepository categoryEventRepository;

    public void addCategoryEvent(CategoryEventRequest categoryEventRequest) {
        CategoryEvent categoryEvent = CategoryEvent.builder()
                .name(categoryEventRequest.getName())
                .build();
        categoryEventRepository.save(categoryEvent);
        log.info("Event category added: {}", categoryEvent);
    }

    public List<CategoryEventResponse> getAllCategoryEvents() {
        List<CategoryEvent> categoryEvents = categoryEventRepository.findAll();
        return categoryEvents.stream()
                .map(event -> new CategoryEventResponse(event.getId(), event.getName()))
                .collect(Collectors.toList());
    }

    public void updateCategoryEvent(long id, CategoryEventRequest categoryEventRequest) {
        CategoryEvent categoryEvent = categoryEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event category not found with id: " + id));

        categoryEvent.setName(categoryEventRequest.getName());
        categoryEventRepository.save(categoryEvent);
        log.info("Updated event category: {}", categoryEvent);
    }

    public void deleteCategoryEvent(long id) {
        categoryEventRepository.deleteById(id);
        log.info("Deleted event category with id: {}", id);
    }
}

