package pe.edu.upc.eventra.notification_service.model.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    private Long notificationId;
    private UserResponse user;
    private String message;
    private boolean isRead;
    private LocalDateTime sentDate;
}


