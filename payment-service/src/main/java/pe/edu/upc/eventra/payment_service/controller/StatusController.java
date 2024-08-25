package pe.edu.upc.eventra.payment_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.payment_service.model.dtos.StatusRequest;
import pe.edu.upc.eventra.payment_service.model.dtos.StatusResponse;
import pe.edu.upc.eventra.payment_service.service.StatusService;

import java.util.List;


@RestController
@RequestMapping("/api/statuses")
@RequiredArgsConstructor
@Tag(name = "StatusController", description = "API for status operations")
public class StatusController {
    private final StatusService statusService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new Status", description = "Adds a new Status to the system")
    public StatusResponse addStatus(@RequestBody StatusRequest statusRequest) {
        return statusService.addStatus(statusRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Statuses", description = "Retrieves a list of all Statuses")
    public List<StatusResponse> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Status by ID", description = "Retrieves a specific Status by ID")
    public StatusResponse getStatusById(@PathVariable("id") long id) {
        return statusService.getStatusById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a Status", description = "Updates a specific Status by ID")
    public StatusResponse updateStatus(@PathVariable("id") long id, @RequestBody StatusRequest statusRequest) {
        return statusService.updateStatus(id, statusRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a Status", description = "Deletes a specific Status by ID")
    public void deleteStatus(@PathVariable("id") long id) {
        statusService.deleteStatus(id);
    }
}

