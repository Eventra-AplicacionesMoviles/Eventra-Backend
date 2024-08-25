package pe.edu.upc.eventra.events_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.events_service.model.dtos.CategoryEventRequest;
import pe.edu.upc.eventra.events_service.model.dtos.CategoryEventResponse;
import pe.edu.upc.eventra.events_service.service.CategoryEventService;

import java.util.List;

@RestController
@RequestMapping("/api/categoryevent")
@RequiredArgsConstructor
@Tag(name = "CategoryEventController", description = "API for event category operations")
public class CategoryEventController {

    private final CategoryEventService categoryEventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new event category", description = "Adds a new event category to the system")
    @ApiResponse(responseCode = "201", description = "Event category created")
    public void addCategoryEvent(@RequestBody CategoryEventRequest categoryEventRequest) {
        categoryEventService.addCategoryEvent(categoryEventRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all event categories", description = "Retrieves a list of all event categories")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of event categories")
    public List<CategoryEventResponse> getAllCategoryEvents() {
        return categoryEventService.getAllCategoryEvents();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an event category", description = "Updates a specific event category by their ID")
    @ApiResponse(responseCode = "200", description = "Event category updated")
    public void updateCategoryEvent(@PathVariable("id") long id, @RequestBody CategoryEventRequest categoryEventRequest) {
        categoryEventService.updateCategoryEvent(id, categoryEventRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryEvent(@PathVariable("id") long id) {
        categoryEventService.deleteCategoryEvent(id);
    }
}

