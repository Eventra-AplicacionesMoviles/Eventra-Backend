package pe.edu.upc.eventra.notification_service.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.notification_service.model.dtos.NotificationRequest;
import pe.edu.upc.eventra.notification_service.model.dtos.NotificationResponse;
import pe.edu.upc.eventra.notification_service.model.dtos.UserResponse;
import pe.edu.upc.eventra.notification_service.model.entities.Notification;
import pe.edu.upc.eventra.notification_service.repository.NotificationRepository;
import pe.edu.upc.eventra.notification_service.repository.UserClient;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserClient userClient;

    @Transactional
    public NotificationResponse addNotification(NotificationRequest notificationRequest) {
        userClient.getUserById(notificationRequest.getUserId());

        Notification notification = Notification.builder()
                .userID(notificationRequest.getUserId())
                .message(notificationRequest.getMessage())
                .isRead(notificationRequest.isRead())
                .sentDate(notificationRequest.getSentDate())
                .build();

        Notification savedNotification = notificationRepository.save(notification);
        log.info("Notification added: {}", savedNotification);
        return mapToNotificationResponse(savedNotification);
    }

    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::mapToNotificationResponse)
                .collect(Collectors.toList());
    }

    public NotificationResponse getNotificationById(long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        return mapToNotificationResponse(notification);
    }

    @Transactional
    public NotificationResponse updateNotification(long id, NotificationRequest notificationRequest) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));

        userClient.getUserById(notificationRequest.getUserId());

        notification.setUserID(notificationRequest.getUserId());
        notification.setMessage(notificationRequest.getMessage());
        notification.setRead(notificationRequest.isRead());
        notification.setSentDate(notificationRequest.getSentDate());

        Notification updatedNotification = notificationRepository.save(notification);
        log.info("Updated Notification: {}", updatedNotification);
        return mapToNotificationResponse(updatedNotification);
    }

    public void deleteNotification(long id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found with id: " + id);
        }
        notificationRepository.deleteById(id);
        log.info("Deleted Notification with id: {}", id);
    }

    private NotificationResponse mapToNotificationResponse(Notification notification) {
        UserResponse userResponse;

        try {
            userResponse = userClient.getUserById(notification.getUserID());
        } catch (FeignException e) {
            log.error("Service is unavailable, unable to fetch details", e);
            userResponse = UserResponse.builder()
                    .userId(null)
                    .firstName(null)
                    .lastName(null)
                    .email(null)
                    .typeOfUser(null)
                    .build();
        }

        return NotificationResponse.builder()
                .notificationId(notification.getNotificationID())
                .user(userResponse)
                .message(notification.getMessage())
                .isRead(notification.isRead())
                .sentDate(notification.getSentDate())
                .build();
    }
}

