package pe.edu.upc.eventra.notification_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.notification_service.model.dtos.NotificationRequest;
import pe.edu.upc.eventra.notification_service.model.dtos.NotificationResponse;
import pe.edu.upc.eventra.notification_service.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "NotificationController", description = "API for notification operations")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new Notification", description = "Adds a new Notification to the system")
    public NotificationResponse addNotification(@RequestBody NotificationRequest notificationRequest) {
        return notificationService.addNotification(notificationRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Notifications", description = "Retrieves a list of all Notifications")
    public List<NotificationResponse> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Notification by ID", description = "Retrieves a specific Notification by ID")
    public NotificationResponse getNotificationById(@PathVariable("id") long id) {
        return notificationService.getNotificationById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a Notification", description = "Updates a specific Notification by ID")
    public NotificationResponse updateNotification(@PathVariable("id") long id, @RequestBody NotificationRequest notificationRequest) {
        return notificationService.updateNotification(id, notificationRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a Notification", description = "Deletes a specific Notification by ID")
    public void deleteNotification(@PathVariable("id") long id) {
        notificationService.deleteNotification(id);
    }
}

